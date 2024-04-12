FROM node:20.11.1-alpine3.19 as build

WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Stage 2: Serve the application with Nginx
FROM nginx:1.21
COPY --from=build /app/dist/my-app /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
