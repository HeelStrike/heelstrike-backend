FROM postgres:17
LABEL authors="superlapp"

# Set environment variables for database initialization
ENV POSTGRES_DB=meal_service_database
ENV POSTGRES_USER=mealsvc
ENV POSTGRES_PASSWORD=secretpw

# Copy SQL schema to the initialization directory
COPY ../sql/recipe_schema.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

#ENTRYPOINT ["top", "-b"]