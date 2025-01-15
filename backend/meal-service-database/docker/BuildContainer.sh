#!/usr/bin/bash
docker run -d \
  --name meal_service_data_container \
  -v meal-service-data:/var/lib/postgresql/data \
  -e POSTGRES_DB=meal_service_data \
  -e POSTGRES_USER=mealsvc \
  -e POSTGRES_PASSWORD=secretpw \
  -p 5432:5432 \
  postgres:17.2
  # REMINDER: Use a unique volume name for each of these! -v option.