import requests
from faker import Faker
import random
from datetime import datetime, timedelta
import argparse
import json
import aiohttp
import asyncio

BASE_URL = "http://localhost:8080"
fake = Faker()

def create_user(full_name, email):
    response = requests.post(
        f"{BASE_URL}/users/",
        json={
            "fio": full_name,
            "email": email,
            #"registrationDate": registration_date
        }
    )
    if response.status_code != 200:
        print(f"Ошибка {response.status_code}: {response.text}")
    return response.json() if response.status_code == 200 else None

def create_post(owner_id, text):
    response = requests.post(
        f"{BASE_URL}/posts/",
        json={
            "owner": owner_id,
            "Text": text,
            #"publicationDate": publication_date,
            #"owner": {"identifier": owner_id}
        }
    )
    if response.status_code != 200:
        print(f"Ошибка {response.status_code}: {response.text}")
    return response.json() if response.status_code == 200 else None

def create_like(post_id, user_id, fio):
    response = requests.post(
            f"{BASE_URL}/likes/",
            json={
                "getfio":  fio,
                #"publicationDate": publication_date,
                "postId":  post_id,
                "userId":  user_id
            }
        )
    if response.status_code != 200:
        print(f"Ошибка {response.status_code}: {response.text}")
    return response.json() if response.status_code == 200 else None


def insert_users(n):
    users = []
    for _ in range(n):
        fio = fake.name()
        email = fake.email()
        #registrationDate = fake.date_between(start_date="-1y", end_date="today").isoformat() + "Z"
        user = create_user(fio, email)
        if user:
            users.append(user)
    return users



def insert_posts(n, users):
    posts = []
    for _ in range(n):
        text = fake.text(max_nb_chars=200)
        owner = random.choice(users)
        if not owner:
            print("Ошибка: Список пользователей пуст. Невозможно создать пост.")
            return posts
        #pub_date = fake.date_between(start_date="-1y", end_date="today").isoformat() + "Z"
        post = create_post( owner["identifier"], text)
        if post:
            posts.append(post)
    return posts

def insert_likes(posts, users):
    likes = []
    for post in posts:
        num_likes = random.randint(0, 5)
        for _ in range(num_likes):
            user = random.choice(users)
            #post = random.choice(posts)
            like_date = fake.date_between(start_date="-1y", end_date="today").isoformat() + "Z"
            like = create_like(post["identifier"], user["identifier"], user["fio"]) #Передаем fio
            likes.append(like)
    return likes

def main():
    parser = argparse.ArgumentParser(description='Генерация данных для социальной сети')
    parser.add_argument('--users', type=int, help='Количество пользователей для генерации')
    parser.add_argument('--posts', type=int, help='Количество постов для генерации')

    args = parser.parse_args()

    if args.users:
        print(f"Создаем {args.users} пользователей...")
        users = insert_users(args.users)

        if args.posts and users:
            print(f"Создаем {args.posts} постов...")
            posts = insert_posts(args.posts, users)

            if posts:
                print("Создаем лайки...")
                likes = insert_likes(posts, users)

    print("Генерация данных завершена.")

if __name__ == "__main__":
    main()