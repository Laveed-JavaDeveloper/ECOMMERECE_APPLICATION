INSERT INTO users (full_name, email, password_hash, role) VALUES ('Store Admin', 'admin@mensstore.in', '{noop}admin123', 'ADMIN');

INSERT INTO products (name, price_inr, category, description, image_url, stock_quantity, available_sizes) VALUES
('Men\'s Classic Formal Shirt', 1299.00, 'SHIRTS_FORMAL', 'Tailored formal shirt in crisp cotton for office and event wear.', '/images/shirt_placeholder.svg', 24, 'S,M,L,XL'),
('Men\'s Full Sleeve Casual Shirt', 1099.00, 'SHIRTS_FULL', 'Soft everyday full sleeve shirt with a relaxed silhouette.', '/images/shirt_placeholder.svg', 30, 'S,M,L,XL'),
('Slim Fit Half Sleeve Shirt', 999.00, 'SHIRTS_HALF', 'Lightweight half sleeve shirt for smart casual styling.', '/images/shirt_placeholder.svg', 26, 'S,M,L,XL'),
('Party Wear Shirt', 1499.00, 'SHIRTS_PARTY', 'Sharp styling with a premium finish for evenings out.', '/images/shirt_placeholder.svg', 18, 'S,M,L,XL'),
('Round Neck Cotton T-Shirt', 699.00, 'TSHIRTS_ROUND', 'Breathable round neck tee for daily wear.', '/images/tee_placeholder.svg', 40, 'S,M,L,XL'),
('Polo T-Shirt', 899.00, 'TSHIRTS_POLO', 'Clean polo collar with a premium soft-touch fabric.', '/images/tee_placeholder.svg', 32, 'S,M,L,XL'),
('Formal Trousers', 1599.00, 'PANTS_FORMAL', 'Sharp formal trousers with a modern straight fit.', '/images/pants_placeholder.svg', 20, '30,32,34,36'),
('Cargo Track Pants', 1199.00, 'TRACKPANTS_CARGO', 'Functional cargo track pants with streetwear appeal.', '/images/pants_placeholder.svg', 22, '30,32,34,36'),
('Casual Sneakers', 2199.00, 'SHOES_CASUAL', 'Comfortable sneakers for day-long wear.', '/images/shoes_placeholder.svg', 28, '7,8,9,10,11'),
('Formal Leather Shoes', 2499.00, 'SHOES_FORMAL', 'Polished formal shoes for suits and business wear.', '/images/shoes_placeholder.svg', 15, '7,8,9,10'),
('Hoodie Jacket', 1799.00, 'HOODIES', 'Warm hoodie for layering in cooler evenings.', '/images/hoodie_placeholder.svg', 16, 'S,M,L,XL'),
('Casual Shorts', 799.00, 'SHORTS', 'Easy-fit shorts for weekend comfort.', '/images/pants_placeholder.svg', 35, '30,32,34,36'),
('Sports Track Pants', 1099.00, 'TRACKPANTS_SPORTS', 'Athleisure track pants built for movement.', '/images/pants_placeholder.svg', 27, '30,32,34,36');