#!/usr/bin/bash
docker run -d \
  --name postgres-container \
  -v postgres-data:/var/lib/postgresql/data \
  -e POSTGRES_DB=meal_service_database \
  -e POSTGRES_USER=mealsvc \
  -e POSTGRES_PASSWORD=secretpw \
  -p 5432:5432 \
  postgres:17.2