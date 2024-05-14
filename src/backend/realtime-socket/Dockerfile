# Resmi Node.js imajını temel al
ARG NODE_VERSION=22.1.0

FROM node:${NODE_VERSION}

ENV NODE_ENV=production

# Çalışma dizinini ayarla
WORKDIR /usr/src/app

# package.json ve package-lock.json dosyalarını kopyala
COPY ["package.json", "package-lock.json*", "npm-shrinkwrap.json*", "./"]

# Bağımlılıkları yükle
RUN npm install --production --silent && mv node_modules ../

# Uygulamanın geri kalan kodunu kopyala
COPY . .

# Node.js uygulamanızın çalışacağı portu belirt
EXPOSE 3000

# Root olmayan user ile uygulamayı çalıştır
RUN chown -R node /usr/src/app
USER node

# Uygulamayı çalıştırmak için komut
CMD ["node", "src/server.js"]