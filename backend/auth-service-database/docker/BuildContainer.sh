#!/usr/bin/bash
docker run -d \
  --name auth_service_data_container \
  -v auth-service-data:/var/lib/postgresql/data \
  -e POSTGRES_DB=auth_service_data \
  -e POSTGRES_USER=authsvc \
  -e POSTGRES_PASSWORD=secretpw \
  -p 5433:5432 \
  postgres:17.2
# REMINDER: Use a unique volume name for each of these! -v option.