INSERT INTO `category` (`id_category`, `name`) VALUES
                                                   (1, 'Experimental'),
                                                   (3, 'Inteligences artificielles'),
                                                   (2, 'Utilitaires');

INSERT INTO `science` (`id_science`, `image`, `name`, `price`, `stock`, `category_id_category`) VALUES
                                                                                                    (1, '/img/Aperture_Science_Handheld_Portal_Device_Portal.webp', 'Aperture Science Quantum Tunneling Device', 125000, 16, 1),
                                                                                                    (2, '/img/P2_afp.webp', 'Plaque de foie aérienne d\'Aperture Science', 60000, 25, 1),
                                                                                                    (52, '/img/400px-Excursion_Funnel.jpeg', 'Tunnel D\'éxcurtion d\'Aperture Science', 25000, 9, 1),
                                                                                                    (53, '/img/Early_Grill.jpg', 'Grille d\'émencipation matérielle d\'Aperture Science', 60000, 50, 1),
                                                                                                    (54, '/img/200px-Puzzle_Creator_-_Hard_Light_Bridge.png', 'Ponts de lumière tangible d\'Aperture Science', 160000, 58, 1),
                                                                                                    (55, '/img/tumblr_m9nbkkBi1Z1rnrq03o1_1280.webp', 'Aperture Science Long Fall Boots', 25000, 155, 2),
                                                                                                    (56, '/img/85.2_FM.webp', 'Aperture Science Radio', 100, 5000, 2),
                                                                                                    (57, '/img/Weighted_Storage_Cube_p2.webp', 'Cube de stockage d\'Aperture Science', 500, 600, 2),
                                                                                                    (58, '/img/Portal2_Turret_Standard.webp', 'Tourelle automatique d\'Aperture Science', 150000, 47, 3),
                                                                                                    (59, '/img/Crap_turret.webp', 'Tourelle automatique au rabais d\'Aperture Science', 20000, 150, 3),
                                                                                                    (60, '/img/Wheatley_model.webp', 'Cœur de personalité d\'Aperture Science', 2000000, 2, 3),
                                                                                                    (61, '/img/Companion_Cube_p2.webp', 'Cube de stockage lesté d\'Aperture Science', 1000000, 395, 3),
                                                                                                    (62, '/img/Potatos.webp', 'PotatOS', 1000000, 1, 3);
