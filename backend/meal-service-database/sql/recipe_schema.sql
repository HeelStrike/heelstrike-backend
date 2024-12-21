CREATE DATABASE recipe_db;

\c recipe_db;

CREATE TABLE IF NOT EXISTS allergen (
    id SERIAL PRIMARY KEY,
    allergen_name VARCHAR(16),
    category VARCHAR(5)
);

-- Prevent duplicate Allergen names
-- ALTER TABLE allergen ADD CONSTRAINT unique_name_category UNIQUE (allergen_name);


CREATE TABLE IF NOT EXISTS macro_ingredient (
    id SERIAL PRIMARY KEY,
    macro_ingredient_name VARCHAR(64),
    allergen_id INT,
    FOREIGN KEY (allergen_id) REFERENCES allergen(id)
);

CREATE TABLE IF NOT EXISTS micro_ingredient (
    id SERIAL PRIMARY KEY,
    micro_ingredient_name VARCHAR(64),
    allergen_id INT,
    FOREIGN KEY (allergen_id) REFERENCES allergen(id)
);

CREATE TABLE IF NOT EXISTS diet (
    id SERIAL PRIMARY KEY,
    diet_name VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS nutrient (
    id SERIAL PRIMARY KEY,
    nutrient_name VARCHAR (64),
    measurement VARCHAR (5)
);

-- ALTER TABLE nutrient ADD CONSTRAINT nutrient_name UNIQUE (nutrient_name)

CREATE TABLE IF NOT EXISTS recipe_image (
    id SERIAL PRIMARY KEY,
    image_url TEXT
);

CREATE TABLE IF NOT EXISTS recipe (
    id SERIAL PRIMARY KEY,
    recipe_name VARCHAR(128),
    cooking_instructions TEXT,
    recipe_image_id INT,
    CONSTRAINT fk_recipe_image_id FOREIGN KEY (recipe_image_id) REFERENCES recipe_image(id) ON DELETE SET NULL
);

-- Many-To-Many Intermediary Table: Recipe to Allergen
CREATE TABLE IF NOT EXISTS recipe_allergen (
    recipe_id INT,
    allergen_id INT,
    PRIMARY KEY (recipe_id, allergen_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    FOREIGN KEY (allergen_id) REFERENCES allergen(id)
);

-- Many-To-Many Intermediary Table: Recipe to Nutrient
CREATE TABLE IF NOT EXISTS recipe_nutrient (
    recipe_id INT NOT NULL,
    nutrient_id INT NOT NULL,
    PRIMARY KEY (recipe_id, nutrient_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id) ON DELETE CASCADE,
    FOREIGN KEY (nutrient_id) REFERENCES nutrient(id) ON DELETE CASCADE
);

-- Many-To-Many Intermediary Table: Recipe to MacroIngredient
CREATE TABLE IF NOT EXISTS recipe_macro_ingredient(
    recipe_id INT,
    macro_ingredient_id INT,
    PRIMARY KEY (recipe_id, macro_ingredient_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    FOREIGN KEY (macro_ingredient_id) REFERENCES macro_ingredient(id)
);

-- Many-To-Many Intermediary Table: Recipe to MicroIngredient
CREATE TABLE IF NOT EXISTS recipe_micro_ingredient (
    recipe_id INT,
    micro_ingredient_id INT,
    PRIMARY KEY (recipe_id, micro_ingredient_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    FOREIGN KEY (micro_ingredient_id) REFERENCES micro_ingredient(id)
);

-- Many-To-Many Intermediary Table: Recipe to Diet
CREATE TABLE IF NOT EXISTS dietary_suitability ( -- e.g. Halal, Vegan, Kosher, Keto etc...;
    recipe_id INT NOT NULL,
    diet_id INT NOT NULL,
    PRIMARY KEY (recipe_id, diet_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    FOREIGN KEY (diet_id) REFERENCES diet(id)
);

-- Many-To-Many Intermediary Table: MicroIngredient to Allergen
CREATE TABLE IF NOT EXISTS micro_ingredient_allergen(
    micro_ingredient_id INT,
    allergen_id INT,
    PRIMARY KEY (micro_ingredient_id, allergen_id),
    FOREIGN KEY (micro_ingredient_id) REFERENCES micro_ingredient(id),
    FOREIGN KEY (allergen_id) REFERENCES allergen(id)
);

-- Many-To-Many Intermediary Table: MacroIngredient to Allergen
CREATE TABLE IF NOT EXISTS macro_ingredient_allergen(
    macro_ingredient_id INT,
    allergen_id INT,
    PRIMARY KEY (macro_ingredient_id, allergen_id),
    FOREIGN KEY (macro_ingredient_id) REFERENCES macro_ingredient(id),
    FOREIGN KEY (allergen_id) REFERENCES allergen(id)
);