from kafka import KafkaProducer
import json

producer = KafkaProducer(
    bootstrap_servers=['localhost:9092'],
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

message = {
    "entityType": "USER",
    "operation": "CREATE",
    "payload": {
        "identifier": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "fio": "John Doe",
        "email": "john@example.com",
        "registrationDate": "2023-01-01T12:00:00"
    }
}

producer.send('module1-entities', message)
producer.flush()
print("Message sent successfully")