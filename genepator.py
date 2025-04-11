import requests
from faker import Faker
import random
import argparse

fake = Faker()

class DataGenerator:
    def __init__(self, base_url):
        self.base_url = base_url

    def clear_data(self, endpoint):
        """Очищает все данные в указанной таблице"""
        response = requests.delete(f"{self.base_url}/{endpoint}/clear")
        if response.status_code != 200:
            print(f"Ошибка при очистке {endpoint}: {response.status_code} - {response.text}")
            return False
        print(f"Данные в таблице {endpoint} успешно очищены")
        return True

    def create_user(self):
        """Создает одного пользователя со случайными данными"""
        data = {
            "fio": fake.name(),
            "email": fake.email()
        }
        response = requests.post(f"{self.base_url}/users/", json=data)
        if response.status_code != 200:
            print(f"Ошибка создания пользователя: {response.status_code} - {response.text}")
            return None
        return response.json()

    def create_post(self, user_id):
        """Создает один пост для указанного пользователя"""
        data = {
            "owner": user_id,
            "Text": fake.text(max_nb_chars=200)
        }
        response = requests.post(f"{self.base_url}/posts/", json=data)
        if response.status_code != 200:
            print(f"Ошибка создания поста: {response.status_code} - {response.text}")
            return None
        return response.json()

    def create_like(self, post_id, user_id, fio):
        """Создает один лайк для указанного поста и пользователя"""
        data = {
            "getfio": fio,
            "postId": post_id,
            "userId": user_id
        }
        response = requests.post(f"{self.base_url}/likes/", json=data)
        if response.status_code != 200:
            print(f"Ошибка создания лайка: {response.status_code} - {response.text}")
            return None
        return response.json()

def generate_users(generator, count):
    """Генерирует указанное количество пользователей"""
    print(f"Генерация {count} пользователей...")
    users = []
    for _ in range(count):
        user = generator.create_user()
        if user:
            users.append(user)
    print(f"Успешно создано {len(users)} пользователей")
    return users

def generate_posts(generator, count, users):
    """Генерирует указанное количество постов"""
    print(f"Генерация {count} постов...")
    posts = []
    for _ in range(count):
        owner = random.choice(users)
        post = generator.create_post(owner["identifier"])
        if post:
            posts.append(post)
    print(f"Успешно создано {len(posts)} постов")
    return posts

def generate_likes(generator, count, posts, users):
    """Генерирует указанное количество лайков"""
    print(f"Генерация {count} лайков...")
    likes = []

    if not posts or not users:
        print("Невозможно создать лайки: отсутствуют посты или пользователи")
        return likes

    for _ in range(count):
        post = random.choice(posts)
        user = random.choice(users)
        like = generator.create_like(post["identifier"], user["identifier"], user["fio"])
        if like:
            likes.append(like)

    print(f"Успешно создано {len(likes)} лайков")
    return likes

def main():
    parser = argparse.ArgumentParser(description='Генератор тестовых данных для REST API')
    parser.add_argument('--count', type=int, default=500, help='Количество создаваемых объектов')
    parser.add_argument('--endpoint', required=True,
                       choices=['users', 'posts', 'likes', 'clear_all', 'clear_users', 'clear_posts', 'clear_likes'],
                       help='Тип объектов для генерации или очистки')
    parser.add_argument('--base-url', default='http://localhost:8080', help='Базовый URL API')

    args = parser.parse_args()

    generator = DataGenerator(args.base_url)

    # Обработка команд очистки
    if args.endpoint == 'clear_all':
        generator.clear_data('likes')
        generator.clear_data('posts')
        generator.clear_data('users')
        return
    elif args.endpoint == 'clear_users':
        generator.clear_data('users')
        return
    elif args.endpoint == 'clear_posts':
        generator.clear_data('posts')
        return
    elif args.endpoint == 'clear_likes':
        generator.clear_data('likes')
        return

    # Очистка перед генерацией данных
    if args.endpoint == 'users':
        generator.clear_data('users')
    elif args.endpoint == 'posts':
        generator.clear_data('posts')
    elif args.endpoint == 'likes':
        generator.clear_data('likes')

    # Генерация данных
    if args.endpoint == 'users':
        users = generate_users(generator, args.count)
    elif args.endpoint == 'posts':
        user_count = max(10, args.count // 10)
        users = generate_users(generator, user_count)
        posts = generate_posts(generator, args.count, users)
    elif args.endpoint == 'likes':
        user_count = max(10, args.count // 20)
        post_count = max(5, args.count // 10)
        users = generate_users(generator, user_count)
        posts = generate_posts(generator, post_count, users)
        likes = generate_likes(generator, args.count, posts, users)

    print("Генерация данных завершена")

if __name__ == "__main__":
    main()