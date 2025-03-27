\c recipe_db;

INSERT INTO diet (name)
    VALUES
        ('Keto'),
        ('Vegan'),
        ('Gluten Free'),
        ('Dairy Free'),
        ('Halal'),
        ('Kosher'),
        ('Low Fat'),
        ('High Protein'),
        ('All');

INSERT INTO macro_ingredient (name)
    VALUES
    ('Baked Beans'),
    ('Mushroom'),
    ('Tomato'),
    ('Lean Back Bacon Rashers'),
    ('Cold Water'),
    ('Celtic Sea Salt'),
    ('Eggs'),
    ('Black Pepper'),
    ('Olive Oil');

INSERT INTO micro_ingredient (name)
    VALUES
        ('Tomato'),
        ('Salt'),
        ('Haricot Bean'),
        ('Pork'),
        ('Mushroom'),
        ('Water'),
        ('Egg'),
        ('Black Pepper'),
        ('Olive Oil');

INSERT INTO nutrient (name)
VALUES
    ('Vitamin A (Retinol)'),
    ('Vitamin C (Ascorbic Acid)'),
    ('Vitamin D (Calciferol)'),
    ('Vitamin E (Tocopherol)'),
    ('Vitamin K (Phylloquinone)'),
    ('Vitamin B1 (Thiamine)'),
    ('Vitamin B2 (Riboflavin)'),
    ('Vitamin B3 (Niacin)'),
    ('Vitamin B6 (Pyridoxine)'),
    ('Vitamin B12 (Cobalamin)'),
    ('Folate (Vitamin B9)'),
    ('Calcium'),
    ('Iron'),
    ('Magnesium'),
    ('Potassium'),
    ('Zinc'),
    ('Selenium'),
    ('Phosphorus'),
    ('Beta-Carotene'),
    ('Lutein'),
    ('Flavonoids'),
    ('Glutathione'),
    ('Coenzyme Q10'),
    ('Protein'),
    ('Fiber');

INSERT INTO allergen (name)
    VALUES
        ('Nut Allergy'),
        ('Oral Allergy Syndrome'),
        ('Stone Fruit Allergy'),
        ('Insulin Allergy'),
        ('Allium Allergy'),
        ('Histamine Allergy'),
        ('Banana Allergy'),
        ('Gluten Allergy'),
        ('Legume Allergy'),
        ('Salicylate Allergy'),
        ('Broccoli Allergy'),
        ('Cruciferous Allergy'),
        ('Ragweed Allergy'),
        ('Milk Allergy / Lactose Intolerance'),
        ('Mushroom Allergy'),
        ('Hypersensitivity'),
        ('Alpha-gal Syndrome'),
        ('Poultry Allergy'),
        ('Ochratoxin Allergy'),
        ('Corn Allergy'),
        ('Seed Allergy'),
        ('Shellfish Allergy'),
        ('Fish Allergy'),
        ('Nightshade Allergy'),
        ('Sugar Allergy / Intolerance'),
        ('LTP Allergy'),
        ('Citrus Allergy'),
        ('Honey Allergy'),
        ('Beer Allergy'),
        ('Potato Allergy'),
        ('Lactose Intolerance'),
        ('Mint Allergy'),
        ('Soy Allergy'),
        ('Rice Allergy'),
        ('Pepper Allergy'),
        ('Tannin Allergy'),
        ('Thyroid'),
        ('Aquagenic Urticaria'),
        ('Peanut Allergy');

INSERT INTO difficulty (name)
    VALUES
        ('Easy'),
        ('Medium'),
        ('Hard'),
        ('Very Hard');
