# vhs-rental-shop
VHS rental application management system

Create Postgres DB in Docker container
* docker pull postgres:alpine
* docker run --name postgres -e POSTGRES_PASSWORD=123 -d -p 5432:5432 postgres:alpine