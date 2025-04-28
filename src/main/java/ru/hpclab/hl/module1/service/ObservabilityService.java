package ru.hpclab.hl.module1.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ObservabilityService {
    // Хранилище для таймингов (ключ - имя метода/операции)
    private final Map<String, Deque<Long>> timings = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> callCounters = new ConcurrentHashMap<>();

    // Параметры временных интервалов (можно вынести в properties)
    private final long accumulationWindow = 60_000; // 1 минута для накопления
    private final int[] statIntervals = {10_000, 30_000, 60_000}; // 10с, 30с, 1м

    /**
     * Запись времени выполнения операции
     * @param operationName имя операции
     * @param duration время выполнения в наносекундах
     */
    public void recordTiming(String operationName, long duration) {
        timings.computeIfAbsent(operationName, k -> new ConcurrentLinkedDeque<>())
                .add(duration);

        callCounters.computeIfAbsent(operationName, k -> new AtomicLong(0))
                .incrementAndGet();
    }

    /**
     * Очистка устаревших данных (вызывается по расписанию)
     */
    @Scheduled(fixedRate = 10_000) // каждые 10 секунд
    public void cleanupOldData() {
        long now = System.currentTimeMillis();
        long cutoff = now - accumulationWindow;

        timings.forEach((key, deque) -> {
            // Удаляем старые записи (реализация зависит от вашего способа хранения временных меток)
            // В этой упрощенной реализации мы просто ограничиваем размер deque
            while (deque.size() > 10_000) { // защита от переполнения
                deque.removeFirst();
            }
        });
    }

    /**
     * Получение статистики для операции
     * @param operationName имя операции
     * @return статистика по интервалам
     */
    public Map<String, Map<String, Number>> getStatistics(String operationName) {
        Map<String, Map<String, Number>> result = new LinkedHashMap<>();

        Deque<Long> operationTimings = timings.get(operationName);
        if (operationTimings == null || operationTimings.isEmpty()) {
            return result;
        }

        // Копируем данные для потокобезопасной обработки
        List<Long> recentTimings = new ArrayList<>(operationTimings);

        for (int interval : statIntervals) {
            // Фильтруем данные по интервалу (в реальности нужно учитывать временные метки)
            // В упрощенной версии берем последние N записей
            int sampleSize = Math.min(recentTimings.size(), interval / 100); // примерная оценка

            List<Long> intervalData = recentTimings.subList(
                    Math.max(0, recentTimings.size() - sampleSize),
                    recentTimings.size());

            if (!intervalData.isEmpty()) {
                Map<String, Number> stats = new LinkedHashMap<>();

                // Базовая статистика
                long sum = intervalData.stream().mapToLong(Long::longValue).sum();
                OptionalDouble avg = intervalData.stream().mapToLong(Long::longValue).average();
                OptionalLong min = intervalData.stream().mapToLong(Long::longValue).min();
                OptionalLong max = intervalData.stream().mapToLong(Long::longValue).max();

                stats.put("count", intervalData.size());
                stats.put("total", TimeUnit.NANOSECONDS.toMillis(sum));
                stats.put("avg", avg.isPresent() ? TimeUnit.NANOSECONDS.toMillis((long)avg.getAsDouble()) : 0);
                stats.put("min", min.isPresent() ? TimeUnit.NANOSECONDS.toMillis(min.getAsLong()) : 0);
                stats.put("max", max.isPresent() ? TimeUnit.NANOSECONDS.toMillis(max.getAsLong()) : 0);

                // Процентили
                Collections.sort(intervalData);
                stats.put("p95", TimeUnit.NANOSECONDS.toMillis(calculatePercentile(intervalData, 0.95)));
                stats.put("p99", TimeUnit.NANOSECONDS.toMillis(calculatePercentile(intervalData, 0.99)));

                result.put(interval + "ms", stats);
            }
        }

        return result;
    }

    private long calculatePercentile(List<Long> data, double percentile) {
        if (data.isEmpty()) return 0;
        int index = (int) Math.ceil(percentile * data.size()) - 1;
        index = Math.min(index, data.size() - 1);
        return data.get(index);
    }

    /**
     * Получение общего количества вызовов для операции
     */
    public long getCallCount(String operationName) {
        AtomicLong counter = callCounters.get(operationName);
        return counter != null ? counter.get() : 0;
    }
}
