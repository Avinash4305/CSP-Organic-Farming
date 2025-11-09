-- Insert sample farming techniques
INSERT INTO farming_techniques (title, description, steps, category, difficulty, season, region, rating, rating_count, available_offline) VALUES
('Companion Planting', 'Plant complementary crops together to enhance growth and deter pests naturally.', '1. Identify compatible plant pairs\n2. Plan garden layout\n3. Plant at appropriate distances\n4. Monitor growth and adjust', 'Planting', 'Beginner', 'All', 'All', 4.5, 12, true),
('No-Till Farming', 'Minimize soil disturbance to improve soil health and reduce erosion.', '1. Stop tilling operations\n2. Use cover crops\n3. Implement crop rotation\n4. Monitor soil health', 'Soil Management', 'Intermediate', 'All', 'Temperate', 4.2, 8, true),
('Drip Irrigation', 'Efficient water delivery system that minimizes evaporation and runoff.', '1. Design irrigation layout\n2. Install main lines\n3. Place emitters\n4. Set timer and monitor', 'Irrigation', 'Beginner', 'Summer', 'Arid', 4.7, 15, false);

-- Insert sample community posts
INSERT INTO community_posts (title, content, category, likes, views, created_at) VALUES
('Tomato Blight Solutions Needed', 'I''ve been struggling with tomato blight in my greenhouse. The humidity seems to make it worse. Has anyone found effective organic solutions for this problem?', 'QUESTION', 12, 45, NOW() - INTERVAL 2 HOUR),
('Increased Yield by 30% with New Irrigation', 'After implementing a drip irrigation system combined with soil moisture sensors, we''ve seen a significant increase in our vegetable yields while using 40% less water.', 'SUCCESS_STORY', 24, 89, NOW() - INTERVAL 1 DAY);