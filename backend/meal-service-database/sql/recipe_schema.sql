CREATE DATABASE recipe_db;

\c recipe_db;

/*
    Schema for Recipe Database.
    Stores data for recipe information, used in the creation of Meal Plans.
*/

CREATE TABLE IF NOT EXISTS recipe (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    cooking_instructions TEXT,
    preparation_time TEXT,
    cooking_time TEXT,
    serves INT CHECK (serves > 0),
    difficulty_id BIGINT REFERENCES difficulty(id)
);
COMMENT ON TABLE recipe IS 'Table for storing recipe data.';

CREATE TABLE IF NOT EXISTS difficulty(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(9)
);

CREATE TABLE IF NOT EXISTS diet (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(26) NOT NULL UNIQUE
);
COMMENT ON TABLE diet IS 'Table for storing diet names, e.g. Halal, Vegan, Keto, etc.';


CREATE TABLE IF NOT EXISTS recipe_dietary_suitability (
    id BIGSERIAL PRIMARY KEY,
    recipe_id BIGINT NOT NULL REFERENCES recipe(id) ON DELETE CASCADE,
    diet_id BIGINT NOT NULL REFERENCES diet(id) ON DELETE CASCADE,
    UNIQUE (recipe_id, diet_id)
);
COMMENT ON TABLE recipe_dietary_suitability IS 'Joins recipe and diet for dietary suitability.';


CREATE TABLE IF NOT EXISTS nutrient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);
COMMENT ON TABLE nutrient IS 'Table for storing nutrients.';


CREATE TABLE IF NOT EXISTS micro_ingredient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);
COMMENT ON TABLE micro_ingredient IS 'Table for storing micro_ingredients. E.g. Garlic, Tomato, Basil.';


CREATE TABLE IF NOT EXISTS nutrient_micro_ingredient (
    id BIGSERIAL PRIMARY KEY,
    nutrient_id BIGINT NOT NULL REFERENCES nutrient(id) ON DELETE CASCADE,
    micro_ingredient_id BIGINT NOT NULL REFERENCES micro_ingredient(id) ON DELETE CASCADE,
    UNIQUE (nutrient_id, micro_ingredient_id)
);
COMMENT ON TABLE nutrient_micro_ingredient IS 'Joins nutrient and micro_ingredient tables for Many-To-Many relationship.';


CREATE TABLE IF NOT EXISTS macro_ingredient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);
COMMENT ON TABLE macro_ingredient IS 'Table for storing macro_ingredients. E.g. Tomato Sauce.';

CREATE TABLE IF NOT EXISTS difficulty (
    id SERIAL,
    name VARCHAR(9)
);

CREATE TABLE IF NOT EXISTS recipe_difficulty (
    id SERIAL,
    recipe_id BIGINT NOT NULL REFERENCES recipe(id) ON DELETE CASCADE,
    difficulty_id INT NOT NULL REFERENCES difficulty(id) ON DELETE CASCADE ,
    UNIQUE(recipe_id, difficulty_id)
);

CREATE TABLE IF NOT EXISTS micro_ingredient_macro_ingredient (
    id BIGSERIAL PRIMARY KEY,
    macro_ingredient_id BIGINT NOT NULL REFERENCES macro_ingredient(id) ON DELETE CASCADE,
    micro_ingredient_id BIGINT NOT NULL REFERENCES micro_ingredient(id) ON DELETE CASCADE,
    UNIQUE (macro_ingredient_id, micro_ingredient_id)
);
COMMENT ON TABLE micro_ingredient_macro_ingredient IS 'Joins tables micro_ingredient and macro_ingredient for Many-To-Many relationship.';


CREATE TABLE IF NOT EXISTS recipe_macro_ingredient (
    id BIGSERIAL PRIMARY KEY,
    recipe_id BIGINT NOT NULL REFERENCES recipe(id) ON DELETE CASCADE,
    macro_ingredient_id BIGINT NOT NULL REFERENCES macro_ingredient(id) ON DELETE CASCADE,
    UNIQUE (recipe_id, macro_ingredient_id)
);
COMMENT ON TABLE recipe_macro_ingredient IS 'Joins tables macro_ingredient and recipe for Many-To-Many relationship.';


CREATE TABLE IF NOT EXISTS recipe_micro_ingredient (
    id BIGSERIAL PRIMARY KEY,
    recipe_id BIGINT NOT NULL REFERENCES recipe(id) ON DELETE CASCADE,
    micro_ingredient_id BIGINT NOT NULL REFERENCES micro_ingredient(id) ON DELETE CASCADE,
    UNIQUE (recipe_id, micro_ingredient_id)
);
COMMENT ON TABLE recipe_micro_ingredient IS 'Joins tables micro_ingredient and recipe for Many-To-Many relationship.';


CREATE TABLE IF NOT EXISTS allergen (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);
COMMENT ON TABLE allergen IS 'Table for storing allergens.';


CREATE TABLE IF NOT EXISTS recipe_allergen (
    id BIGSERIAL PRIMARY KEY,
    recipe_id BIGINT REFERENCES recipe(id) ON DELETE CASCADE,
    allergen_id BIGINT NOT NULL REFERENCES allergen(id) ON DELETE CASCADE,
    UNIQUE (recipe_id, allergen_id)
);
COMMENT ON TABLE recipe_allergen IS 'Joins tables recipe and allergen for Many-To-Many relationship.';


CREATE TABLE IF NOT EXISTS allergen_micro_ingredient (
    id BIGSERIAL PRIMARY KEY,
    allergen_id BIGINT REFERENCES allergen(id) ON DELETE CASCADE,
    micro_ingredient_id BIGINT REFERENCES micro_ingredient(id) ON DELETE CASCADE,
    UNIQUE(allergen_id, micro_ingredient_id)
);
COMMENT ON TABLE allergen_micro_ingredient IS 'Joins tables micro_ingredient and allergen for Many-To-Many relationship.';
