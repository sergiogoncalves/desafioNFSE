# Etapa 1: build do Angular
FROM node:18-alpine AS build-stage

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN rm -rf dist/
RUN npm run build

# Etapa 2: nginx serve os arquivos do dist
FROM nginx:alpine

COPY --from=build-stage /app/dist/frontend /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
