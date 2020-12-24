--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-12-24 10:30:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2973 (class 0 OID 42883)
-- Dependencies: 218
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (2, '36', 'rue de la république', 'derrière la maison 35', '69680', 'Chassieu');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (1, '25', 'rue Oreste Zenezini', NULL, '69680', 'Chassieu');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (3, '78', 'Route de Lyon', NULL, '69680', 'Chassieu');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (4, '58', 'chemin du trèves', NULL, '69680', 'Chassieu');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (5, '65', 'rue Victor Hugo', 'Au fond de l''impasse', '69680', 'Chassieu');


--
-- TOC entry 2974 (class 0 OID 42892)
-- Dependencies: 219
-- Data for Name: bibliotheque; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200177', 'Bibliothèque Molière', 'AEZ', 1);
INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200568', 'Bibliothèque Victor Hugo', 'AFB', 3);
INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200356', 'Bibliothèque Emile Zola', 'AFA', 2);


--
-- TOC entry 2971 (class 0 OID 42873)
-- Dependencies: 216
-- Data for Name: enumrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.enumrole (code, status, description) VALUES (1, 'Abonné', 'Abonné');
INSERT INTO public.enumrole (code, status, description) VALUES (2, 'Administrateur', 'Administrateur');
INSERT INTO public.enumrole (code, status, description) VALUES (3, 'Directeur', 'Directeur des bibliothèques');
INSERT INTO public.enumrole (code, status, description) VALUES (4, 'Gérant', 'Responsable de la bibliothèque');
INSERT INTO public.enumrole (code, status, description) VALUES (5, 'Employé', 'Employé dans une bibliothèque');


--
-- TOC entry 2978 (class 0 OID 42913)
-- Dependencies: 223
-- Data for Name: abonne; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.abonne (id_abonne, nom, prenom, pseudo, email, mot_de_passe, numero_abonne, date_creation_du_compte, role, id_adresse, bibliotheque) VALUES (1, 'Dujardin', 'Jean', 'Jeannot', 'dujardin.jean@gmail.com', '1234', '456132245', '2018-12-14', 1, 5, '18004625200177');


--
-- TOC entry 2964 (class 0 OID 42831)
-- Dependencies: 209
-- Data for Name: auteur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (1, 'Hugo', 'Victor', '1802-02-26', '1885-06-22', 'Victor Hugo est un poète, dramaturge, écrivain, romancier et dessinateur romantique français, né le 7 ventôse an X (26 février 1802) à Besançon et mort le 22 mai 1885 à Paris. Il est considéré comme l''un des plus importants écrivains de langue française. Il est aussi une personnalité politique et un intellectuel engagé qui a eu un rôle idéologique majeur et occupe une place marquante dans l''histoire des lettres françaises au XIXe siècle, dans des genres et des domaines d’une remarquable variété.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (2, 'Saint-Exupery', 'Antoine', '1900-06-29', '1944-07-31', 'Antoine de Saint-Exupéry, né le 29 juin 1900 à Lyon et disparu en vol le 31 juillet 1944 au large de Marseille, est un écrivain, poète, aviateur et reporter français.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (3, 'Coelho', 'Paulo', '1947-08-24', NULL, 'Paulo Coelho de Souza, né le 24 août 1947 à Rio de Janeiro, est un romancier, journaliste et un interprète brésilien. Il a acquis une renommée internationale avec la publication de L''Alchimiste, vendu à 85 millions d''exemplaires. En ajoutant ses autres ouvrages, ce sont 350 millions d''exemplaires vendus à travers le monde. Ses romans ont été traduits dans 81 langues du monde entier. ');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (4, 'Obama', 'Barack', '1961-08-04', NULL, 'Barack Obama né le 4 août 1961 à Honolulu (Hawaï), est un homme d''État américain. Il est le 44e président des États-Unis, en fonction du 20 janvier 2009 au 20 janvier 2017. Fils d''un Kényan noir et d''une Américaine blanche du Kansas d’ascendance anglaise et irlandaise, il est élevé durant plusieurs années en Indonésie. Diplômé de l''université Columbia de New York et de la faculté de droit de Harvard, il est, en 1990, le premier Afro-Américain à présider la prestigieuse Harvard Law Review. Après avoir été travailleur social dans les quartiers sud de Chicago durant les années 1980, puis avocat en droit civil à sa sortie de Harvard, il enseigne le droit constitutionnel à l''université de Chicago de 1992 à 2004. ');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (5, 'Tolle', 'Eckhart', '1948-02-16', NULL, 'Eckhart Tolle, de son vrai nom Ulrich Leonard Tolle, né le 16 février 1948 à Lünen (Allemagne), est un écrivain et conférencier canadien d''origine allemande, auteur des best-sellers Le Pouvoir du moment présent et Nouvelle Terre. Tolle raconte qu’il s''est senti déprimé toute la première partie de sa vie jusqu’à ce qu’il connût, à l’âge de 29 ans, une « transformation intérieure » à la suite de laquelle il passa plusieurs années sans emploi à vagabonder « dans un état de félicité profonde » avant de devenir enseignant spirituel. Plus tard, il déménagea en Amérique du Nord où il commença à écrire son premier livre, Le Pouvoir du moment présent, qui fut publié en 1997 aux États-Unis et est devenu un best-seller international traduit en 33 langues1. Tolle s’installa ensuite à Vancouver au Canada où il vit depuis. ');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (6, 'Pagnol', 'Marcel', '1895-02-28', '1974-05-18', 'Marcel Pagnol est un écrivain, dramaturge, cinéaste et producteur français, né le 28 février 1895 à Aubagne (Bouches-du-Rhône) et mort à Paris le 18 avril 1974. Il devient célèbre avec Marius, pièce représentée au théâtre en mars 1929. Il fonde à Marseille en 1934 sa propre société de production et ses studios de cinéma, et réalise de nombreux films avec les grands acteurs de la période (en particulier Raimu, Fernandel et Pierre Fresnay), dont Angèle (1934), Regain (1937) et La Femme du boulanger (1938).');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (7, 'Morris', ' ', '2020-12-01', '2001-01-16', 'Maurice de Bevere, dit Morris, né le 1er décembre 1923 à Courtrai et mort le 16 juillet 2001 à Bruxelles, est un auteur de bandes dessinées belge connu comme créateur en 1946 de Lucky Luke, série populaire qu''il a dessinée jusqu''à sa mort, seul ou en collaboration avec divers scénaristes, dont René Goscinny. Il a qualifié pour la première fois la bande dessinée de « neuvième art » dans le journal Spirou, en 1965.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (8, 'Lucas', 'George', '1944-05-14', NULL, 'George Lucas est un réalisateur, scénariste et producteur américain né le 14 mai 1944 à Modesto en Californie. Issu de l''école de cinéma de l''université de Californie du Sud à Los Angeles, il cofonde avec son ami Francis Ford Coppola le studio American Zoetrope puis crée sa propre société de production : Lucasfilm. Il commence sa carrière de réalisateur avec les films THX 1138 en 1971 et American Graffiti en 1973. Il connaît ensuite la consécration avec les deux premières trilogies cinématographiques Star Wars. Énorme succès au box-office, la saga galactique fait de lui un des cinéastes les plus reconnus et fortunés du cinéma américain mais aussi complique sa vie, notamment à cause de ses relations parfois difficiles avec les fans.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (9, 'Tolkien', 'J.R.R.', '1892-01-03', '1973-09-02', 'John Ronald Reuel Tolkien, plus connu sous la forme J. R. R. Tolkien, est un écrivain, poète, philologue, essayiste et professeur d’université britannique, né le 3 janvier 1892 à Bloemfontein (État libre d''Orange) et mort le 2 septembre 1973 à Bournemouth (Royaume-Uni). Il est principalement connu pour ses romans Le Hobbit et Le Seigneur des anneaux.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (10, 'Hergé', ' ', '1907-05-22', '1983-03-03', 'Georges Remi dit Hergé, né le 22 mai 1907 à Etterbeek et mort le 3 mars 1983 à Woluwe-Saint-Lambert, est un auteur belge de bande dessinée principalement connu pour Les Aventures de Tintin, désignée aujourd''hui comme l''une des bandes dessinées européennes les plus populaires du XXe siècle.dit Hergé, né le 22 mai 1907 à Etterbeek et mort le 3 mars 1983 à Woluwe-Saint-Lambert, est un auteur belge de bande dessinée principalement connu pour Les Aventures de Tintin, désignée aujourd''hui comme l''une des bandes dessinées européennes les plus populaires du XXe siècle.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (11, 'Goscinny', 'René', '1926-08-14', '1977-11-05', 'René Goscinny, né le 14 août 1926 à Paris où il est mort le 5 novembre 1977, est un scénariste de bande dessinée, journaliste, écrivain et humoriste français, également producteur, réalisateur et scénariste de films.

Il est l''un des fondateurs et rédacteurs en chef de Pilote, l''un des principaux journaux français de bande dessinée. Créateur d''Astérix avec Albert Uderzo, d''Iznogoud avec Jean Tabary, auteur du Petit Nicolas, personnage créé et dessiné par Jean-Jacques Sempé, scénariste de nombreux albums de Lucky Luke créé par Morris. Il est l’un des auteurs français les plus lus au monde : l’ensemble de son œuvre représente environ 500 millions d’ouvrages vendus.');


--
-- TOC entry 2982 (class 0 OID 51346)
-- Dependencies: 227
-- Data for Name: editeur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (1, 'Gallimard');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (2, 'J''ai Lu');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (3, 'casterman');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (4, 'Omnibus');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (5, 'Dargaud');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (6, 'Fayard');


--
-- TOC entry 2976 (class 0 OID 42902)
-- Dependencies: 221
-- Data for Name: employe; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employe (id_employe, nom, prenom, matricule, date_embauche, date_depart, email, mot_de_passe, bibliotheque, role, id_adresse) VALUES (1, 'Dupont', 'Régis', '168954632', '2017-12-15', NULL, 'dupont.regis@yahoo.fr', '55568985', '18004625200177', 5, 4);


--
-- TOC entry 2968 (class 0 OID 42853)
-- Dependencies: 213
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.genre (id_genre, nom, description) VALUES (1, 'Conte philosophique', 'Le conte philosophique, genre littéraire né au XVIIIe siècle, est une histoire fictive, critique de la société et du pouvoir en place pour transmettre des idées, concepts à portée philosophique : mœurs de la noblesse, régimes politiques, fanatisme religieux ou encore certains courants philosophiques. Il reprend la construction du conte et utilise certaines de ses formulations comme « il était une fois », dans le but de se soustraire à la censure qui sévit à cette époque. Il appartient, comme lui, au genre de l''apologue, court récit allégorique et argumentatif dont on tire une morale, et qui regroupe aussi, entre autres, la fable et l''utopie.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (2, 'Roman', 'Le roman est un genre littéraire caractérisé essentiellement par une narration fictionnelle et dont la première apparition peut être datée du XIIe siècle. Initialement écrit en vers qui jouent sur les assonances, il est écrit en prose dès le XIIe siècle et se distingue du conte ou de l''épopée par sa vocation à être lu individuellement et non écouté.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (3, 'Science-Fiction', 'La science-fiction n''est-elle pas un genre en soi ? C''est ce que suggère cette définition issue d''une ancienne encyclopédie « Focus » : « Genre où l''auteur donne libre cours à son imagination en s''appuyant sur les progrès de la science et sur ses possibilités de découvertes ».

Il existe un grand nombre de genres et de sous-genres de la science-fiction, qui se confondent et s''articulent entre des thématiques, des modes opératoires, des supports de diffusion et enfin des écoles par nationalité des auteurs');
INSERT INTO public.genre (id_genre, nom, description) VALUES (4, 'Autobiographie', 'L’autobiographie est un genre littéraire et artistique. Son étymologie grecque définit le fait d’écrire (graphè, graphie) sur sa propre vie (auto, soi ; et bios, vie). Au sens large l’autobiographie se caractérise donc au moins par l’identité de l’auteur, du narrateur et du personnage. Le mot est assez récent, il n’est fabriqué qu’au début du xixe siècle (1815 en anglais, 1832 pour l’adjectif et 1842 pour le substantif en français). L’approche actuelle parle dans ce cas plutôt de « genre autobiographique », réservant à « autobiographie » un sens plus étroit qu’a établi Philippe Lejeune dans les années 1970.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (5, 'Bande dessinée', 'Une bande dessinée (dénomination communément abrégée en BD ou en bédé) est une forme d''expression artistique, souvent désignée comme le « neuvième art », utilisant une juxtaposition de dessins (ou d''autres types d''images fixes, mais pas uniquement photographiques), articulés en séquences narratives et le plus souvent accompagnés de textes (narrations, dialogues, onomatopées). Will Eisner, un des plus grands auteurs de bande dessinée, l''a définie (avant l''émergence d''Internet) comme « la principale application de l''art séquentiel au support papier »');
INSERT INTO public.genre (id_genre, nom, description) VALUES (6, 'Aventure', 'Le roman d’aventures est un type de roman populaire qui met particulièrement l''accent sur l''action en multipliant les péripéties plutôt violentes, dans lequel le héros est plutôt masculin et jeune, en général positifs, et où le souci de la forme littéraire est relativement peu important.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (7, 'Humoristique', 'La bande dessinée humoristique ou bande dessinée d''humour est le premier type de bande dessinée à avoir été développé. Les bandes dessinées humoristiques existent dans tous types de formats (comic strip, Gag, récit complet, etc.) et peuvent s''adresser selon le type d''humour aux enfants comme aux adultes, voire à tous les publics grâce à différents niveaux de lecture1. Il est resté le genre dominant jusqu''à l''émergence des bandes dessinées d''aventure réalistes au tournant des années 1930 aux États-Unis puis dans le reste du monde');
INSERT INTO public.genre (id_genre, nom, description) VALUES (8, 'Développement Personnel', 'Le développement personnel est un ensemble hétéroclite de pratiques, appartenant à divers courants de pensées1, qui ont pour objectif l''amélioration de la connaissance de soi2, la valorisation des talents et potentiels3, l''amélioration de la qualité de vie personnelle, la réalisation de ses aspirations et de ses rêves4. Le développement personnel n''est toutefois pas une sorte de psychothérapie1 et résulte d''influences multiples. En effet, la psychologie et la philosophie, et souvent la diététique et la pratique du sport, fondent généralement les pratiques des acteurs du développement personnel moderne ; d''autres y rattachent également des notions religieuses ou relevant de l''ésotérisme');
INSERT INTO public.genre (id_genre, nom, description) VALUES (9, 'Fantastique', 'Une œuvre est de genre Fantastique quand elle relate des événements totalement étranges, le plus souvent irrationnels ou incompréhensibles, hors d''atteinte de la puissance humaine ou de l''explication rationnelle (apparition de doubles, de fantômes, de spectres ou de revenants ; labyrinthes étranges ; rêves ou prémonitions ; réincarnation ; événements maléfiques inexpliqués ou apparition de démons ; apparition d''anges ou d''anges gardien ; objets usuellement inertes mais devenus vivants, etc).');


--
-- TOC entry 2958 (class 0 OID 42798)
-- Dependencies: 203
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (1, 'Le Petit Prince', '« Le premier soir je me suis donc endormi sur le sable à mille milles de toute terre habitée. J''étais bien plus isolé qu''un naufragé sur un radeau au milieu de l''Océan. Alors vous imaginez ma surprise, au lever du jour, quand une drôle de petite voix m''a réveillé. Elle disait :
- S''il vous plaît... dessine-moi un mouton !
- Hein !
- Dessine-moi un mouton...
J''ai sauté sur mes pieds comme si j''avais été frappé par la foudre. J''ai bien frotté mes yeux. J''ai bien regardé.
Et j''ai vu un petit bonhomme tout à fait extraordinaire qui me considérait gravement. » ', '2007-03-15', '978-2-070-61275-8', 'Français', 113, 'petit_prince_petit.jpg', 'petit_prince_grand.jpg', 1, 'petit_prince_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (8, 'Star Wars - La Guerre des Etoiles ', 'La guerre des étoiles , première épisode de la trilogie fondatrice.
Il y a bien longtemps, dans une galaxie lointaine, très lointaine...
C''est une époque de guerre civile. À bord de vaisseaux spatiaux opérant à partir d''une base inconnue, les Rebelles ont remporté leur première victoire sur l''abominable Empire Galactique.
Au cours de la bataille, les Rebelles ont réussi à dérober les plans secrets de l''arme absolue de l''Empire : L''Étoile Noire, une station spatiale dotée d''un armement assez puissant pour annihiler une planète tout entière.
Poursuivie par les sbires sinistres de l''Empire, la princesse Leia regagne sa base dans son vaisseau cosmique, porteuse des plans volés à l''ennemi qui pourraient sauver son peuple et restaurer la liberté dans la galaxie...', '2015-11-26', '978-2-785-00440-9', 'Français', 1012, 'star_wars_petit.jpg', 'star_wars_grand.jpg', 4, 'star_wars_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (9, 'Le Seigneur des Anneaux', 'Une contrée paisible où vivent les Hobbits. Un anneau magique à la puissance infinie. Sauron, son créateur, prêt à dévaster le monde entier pour récupérer son bien. Frodon, jeune Hobbit, détenteur de l''Anneau malgré lui. Gandalf, le Magicien, venu avertir Frodon du danger. Et voilà déjà les Cavaliers Noirs qui approchent... C''est ainsi que tout commence en Terre du Milieu entre la Comté et Mordor. C''est ainsi que la plus grande légende est née.', '2018-09-13', '978-2-266-28626-8', 'Français', 1592, 'seigneurs_petit.jpg', 'seigneurs_grand.jpg', 1, 'seigneurs_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (7, 'Daisy Town', 'Daisy Town, une grande ville moderne du Far West, est livrée à la corruption. Il faudra peu de temps à Lucky Luke pour rétablir l''ordre. Mais c''est sans compter sur l''arrivée des Dalton en ville qui commencent à terroriser la population couarde et tentent de se faire élire shérifs à la place de Lucky Luke. Après s''être fait bouter en bonne est due forme de la ville, ils vont tout faire pour se venger et faire raser la ville.', '2001-02-20', '978-2-884-71036-7', 'Français', 46, 'daisy_town_petit.jpg', 'daisy_town_grand.jpg', 5, 'daisy_town_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (2, 'Les Misérables', 'Jean Valjean, les Thénardier, Cosette sont entrés au panthéon de la littérature. Porté par ses personnages, Victor Hugo a écrit avec les Misérables un hymne à la littérature, profondément ancré dans la mentalité collective de son temps. Une épopée du peuple aux prises avec l''Histoire, pleine debruits et de fureur.', '2008-08-22', '978-2-035-83425-6', 'Français', 270, 'les_miserables_petit.jpg', 'les_miserables_grand.jpg', 1, 'les_miserables_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (10, 'On a marché sur la Lune', 'Après un voyage spatial mouvementé, Tintin, Milou, le capitaine Haddock, le professeur Tournesol et l''ingénieur Wolff atteignent la Lune. L''exploration de ce nouveau monde leur réserve encore bien des surprises... ', '1993-05-04', '978-2-203-00116-9', 'Français', 61, 'tintin_petit.jpg', 'tintin_grand.jpg', 3, 'tintin_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (5, 'Le Pouvoir du Moment Présent', 'Le pouvoir du moment présent est probablement l''un des livres les plus importants de notre époque.
Son enseignement simple et néanmoins profond a aidé des millions de gens à travers le monde à trouver la paix intérieure et à se sentir plus épanouis dans leur vie. Au coeur de cet enseignement se trouve la transformation de la conscience : en vivant dans l''instant présent, nous transcendons notre ego et accédons à " un état de grâce, de légèreté et de bien-être ". Ce livre a le pouvoir de métamorphoser votre vie par une expérience unique.', '2010-08-28', '978-2-290-02020-3', 'Français', 253, 'present_petit.jpg', 'present_grand.jpg', 2, 'present_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (3, 'L''Alchimiste', 'santiago, un jeune berger andalou, part à la recherche d''un trésor enfoui au pied des pyramides.
lorsqu''il rencontre l''alchimiste dans le désert, celui-ci lui apprend à écouter son coeur, à lire les signes du destin et, par-dessus tout, à aller au bout de son rêve.merveilleux conte philosophique destiné à l''enfant qui sommeille en chaque être, ce livre a déjà marqué une génération de lecteurs.', '2007-03-01', '978-2-290-00444-9', 'Français', 190, 'l_Alchimiste_petit.jpg', 'l_Alchimiste_grand.jpg', 2, 'l_Alchimiste_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (4, 'Une Terre Promise', 'Un récit fascinant et profondément intime de l''histoire en marche, par le président qui nous a insufflé la foi dans le pouvoir de la démocratie.

Dans le premier volume passionnant et très attendu de ses mémoires présidentiels, Barack Obama raconte l''histoire de son improbable odyssée, de jeune homme en quête d''identité à dirigeant du monde libre, retraçant de manière singulièrement détaillée et personnelle son éducation politique et les moments emblématiques du premier mandat de sa présidence historique - une période de transformations et de bouleversements profonds.

Barack Obama entraîne le lecteur dans un voyage fascinant, depuis ses toutes premières aspirations politiques à sa victoire cruciale aux primaires de l''Iowa, qui démontra le pouvoir de l''engagement citoyen, jusqu''à la soirée décisive du 4 novembre 2008, lorsqu''il fut élu 44e président des États-Unis, devenant ainsi le premier Afro-Américain à accéder à la fonction suprême.', '2020-11-17', '978-2-213-70612-2', 'Français', 890, 'barack_petit.jpg', 'barack_grand.jpg', 6, 'barack_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (6, 'Le Château de ma Mère', 'Le plus beau livre sur l''amitié enfantine : un matin de chasse dans les collines. Marcel rencontre le petit paysan, Lili des Bellons. Ses vacances et sa vie entière en seront illuminées.
Un an après La Gloire de mon père, Marcel Pagnol pensait conclure ses Souvenirs d''enfance avec ce Château de ma mère (1958), deuxième volet de ce qu''il considérait comme un diptyque, s''achevant sur la scène célèbre du féroce gardien effrayant la timide Augustine. Le petit Marcel, après la tendresse familiale, a découvert l''amitié avec le merveilleux Lili, sans doute le plus attachant de ses personnages. Le livre se clôt sur un épilogue mélancolique, poignante élégie au temps qui a passé. Pagnol y fait vibrer les cordes d''une gravité à laquelle il a rarement habitué ses lecteurs.
Je ris un garçon de mon âge qui me regardait sévèrement. "Il ne faut pas toucher les pièges des autres, dit-il. Un piège, c''est sacré ! - Je n''allais pas le prendre, dis-je. Je voulais voir l''oiseau. '''' Il s''approcha : c''était un petit paysan. Il était brun, avec un fin ri sage provençal, des veux noirs et de longs cils de fille. " ', '2004-08-25', '978-2-877-06508-5', 'Français', 220, 'chateau_petit.jpg', 'chateau_grand.jpg', 2, 'chateau_moyen.jpg');


--
-- TOC entry 2970 (class 0 OID 42864)
-- Dependencies: 215
-- Data for Name: liste_genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (1, 1, 1);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (2, 2, 2);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (3, 3, 1);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (4, 4, 4);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (5, 5, 8);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (6, 6, 2);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (7, 7, 5);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (8, 7, 7);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (9, 8, 3);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (10, 9, 9);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (11, 10, 5);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (12, 10, 6);


--
-- TOC entry 2966 (class 0 OID 42842)
-- Dependencies: 211
-- Data for Name: livre_auteurs; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (1, 1, 2);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (2, 2, 1);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (3, 3, 3);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (4, 4, 4);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (5, 5, 5);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (6, 6, 6);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (7, 7, 7);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (8, 7, 11);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (9, 8, 8);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (10, 9, 9);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (11, 10, 10);


--
-- TOC entry 2960 (class 0 OID 42809)
-- Dependencies: 205
-- Data for Name: ouvrage; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (1, 'AEZCON1355', 1, '2016-08-10');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (3, 'AEZCON855', 3, '2018-12-20');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (4, 'AEZCON856', 4, '2018-12-21');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (5, 'AEZCON857', 5, '2018-12-22');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (6, 'AEZCON858', 6, '2018-12-23');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (7, 'AEZCON859', 7, '2018-12-24');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (8, 'AEZCON860', 8, '2018-12-25');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (9, 'AEZCON861', 9, '2018-12-26');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (10, 'AEZCON862', 10, '2018-12-27');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout) VALUES (2, 'AEZROM565', 2, '2019-12-19');


--
-- TOC entry 2962 (class 0 OID 42820)
-- Dependencies: 207
-- Data for Name: pret; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage) VALUES (1, '2020-12-04', NULL, false, 1);
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage) VALUES (2, '2020-12-04', NULL, false, 2);


--
-- TOC entry 2980 (class 0 OID 42924)
-- Dependencies: 225
-- Data for Name: pret_abonne; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pret_abonne (id_pret_abonne, id_pret, id_abonne) VALUES (1, 1, 1);
INSERT INTO public.pret_abonne (id_pret_abonne, id_pret, id_abonne) VALUES (2, 2, 1);


--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 222
-- Name: abonne_id_abonne_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.abonne_id_abonne_seq', 1, false);


--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 217
-- Name: adresse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adresse_id_seq', 1, true);


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 208
-- Name: auteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auteur_id_seq', 1, false);


--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 226
-- Name: editeur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.editeur_id_seq', 1, false);


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 220
-- Name: employe_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employe_id_employe_seq', 1, false);


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 212
-- Name: genre_nom_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genre_nom_seq', 1, false);


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 214
-- Name: liste_genre_id_liste_genre_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.liste_genre_id_liste_genre_seq', 1, false);


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 210
-- Name: livre_auteurs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_auteurs_id_seq', 1, false);


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 202
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_id_seq', 1, false);


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 204
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ouvrage_id_ouvrage_seq', 1, false);


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 224
-- Name: pret_abonne_id_pret_abonne_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pret_abonne_id_pret_abonne_seq', 1, false);


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 206
-- Name: pret_id_pret_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pret_id_pret_seq', 1, false);


-- Completed on 2020-12-24 10:30:21

--
-- PostgreSQL database dump complete
--

