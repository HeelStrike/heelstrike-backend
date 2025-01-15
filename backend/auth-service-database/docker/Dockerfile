FROM postgres:17
LABEL authors="heelstrike"

# Set environment variables for database initialization
ENV POSTGRES_DB=auth_service_data
ENV POSTGRES_USER=authsvc
ENV POSTGRES_PASSWORD=secretpw

# Copy SQL schema to the initialization directory
COPY ../sql/recipe_schema.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

#ENTRYPOINT ["top", "-b"]