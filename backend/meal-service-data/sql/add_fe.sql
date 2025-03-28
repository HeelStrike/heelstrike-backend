DO $$
    DECLARE
        new_recipe_id BIGINT;
    BEGIN
        -- Insert recipe and store the ID
        INSERT INTO recipe (title, description, cooking_instructions, preparation_time, cooking_time, serves, difficulty_id)
        VALUES (
                   'Spinach Egg Scramble',
                   'A healthy high-protein breakfast with spinach, eggs, and olive oil.',
                   '1. Whisk eggs\n2. Heat olive oil\n3. Add spinach and cook\n4. Add eggs and scramble\n5. Season and serve.',
                   'PT5M',
                   'PT10M',
                   2,
                   (SELECT id FROM difficulty WHERE name = 'Medium')
               )
        RETURNING id INTO new_recipe_id;

        -- Link to diets
        INSERT INTO recipe_dietary_suitability (recipe_id, diet_id)
        SELECT new_recipe_id, d.id
        FROM diet d
        WHERE d.name IN ('Gluten Free', 'High Protein');

        -- Link macro ingredients
        INSERT INTO recipe_macro_ingredient (recipe_id, macro_ingredient_id)
        SELECT new_recipe_id, m.id
        FROM macro_ingredient m
        WHERE m.name IN ('Eggs', 'Olive Oil');

        -- Link macro to micro ingredients
        INSERT INTO micro_ingredient_macro_ingredient (macro_ingredient_id, micro_ingredient_id)
        SELECT m.id, mi.id
        FROM macro_ingredient m, micro_ingredient mi
        WHERE (m.name, mi.name) IN (
                                    ('Eggs', 'Egg'),
                                    ('Eggs', 'Black Pepper'),
                                    ('Olive Oil', 'Olive Oil')
            )
        ON CONFLICT DO NOTHING;

        -- Link micro ingredients to nutrients
        INSERT INTO nutrient_micro_ingredient (nutrient_id, micro_ingredient_id)
        SELECT n.id, mi.id
        FROM nutrient n, micro_ingredient mi
        WHERE n.name IN ('Protein', 'Vitamin A (Retinol)')
          AND mi.name IN ('Egg', 'Olive Oil')
        ON CONFLICT DO NOTHING;
    END
$$;
