#!/bin/bash

echo "======================================"
echo "🚨 Removendo TODOS os volumes Docker (cuidado, global)..."
echo "======================================"
docker volume rm $(docker volume ls -q) || true

echo "======================================"
echo "🧹 Parando containers do projeto + removendo volumes locais..."
echo "======================================"
docker-compose down -v

echo "======================================"
echo "🛠️ Limpando build do backend (Gradle clean + build)..."
echo "======================================"
cd backend || exit
./gradlew clean build -x test
cd ..

echo "======================================"
echo "🧹 Limpando build do frontend..."
echo "======================================"
cd frontend || exit
rm -rf dist node_modules
npm install
npm run build
cd ..

echo "======================================"
echo "🐳 Subindo todos os serviços com build limpo..."
echo "======================================"
docker-compose up --build
