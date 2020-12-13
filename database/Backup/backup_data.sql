--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-12-13 09:12:19

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
-- TOC entry 2975 (class 0 OID 42883)
-- Dependencies: 220
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (2, '36', 'rue de la république', 'derrière la maison 35', '69680', 'Chassieu');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (1, '25', 'rue Oreste Zenezini', NULL, '69680', 'Chassieu');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (3, '78', 'Route de Lyon', NULL, '69680', 'Chassieu');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (4, '58', 'chemin du trèves', NULL, '69680', 'Chassieu');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (5, '65', 'rue Victor Hugo', 'Au fond de l''impasse', '69680', 'Chassieu');


--
-- TOC entry 2976 (class 0 OID 42892)
-- Dependencies: 221
-- Data for Name: bibliotheque; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200177', 'Bibliothèque Molière', 'AEZ', 1);
INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200356', 'Bibliothèque Emile', 'AFA', 2);
INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200568', 'Bibliothèque Victor', 'AFB', 3);


--
-- TOC entry 2973 (class 0 OID 42873)
-- Dependencies: 218
-- Data for Name: enumrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.enumrole (code, status, description) VALUES (1, 'Abonné', 'Abonné');
INSERT INTO public.enumrole (code, status, description) VALUES (2, 'Administrateur', 'Administrateur');
INSERT INTO public.enumrole (code, status, description) VALUES (3, 'Directeur', 'Directeur des bibliothèques');
INSERT INTO public.enumrole (code, status, description) VALUES (4, 'Gérant', 'Responsable de la bibliothèque');
INSERT INTO public.enumrole (code, status, description) VALUES (5, 'Employé', 'Employé dans une bibliothèque');


--
-- TOC entry 2980 (class 0 OID 42913)
-- Dependencies: 225
-- Data for Name: abonne; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.abonne (id_abonne, nom, prenom, pseudo, email, mot_de_passe, numero_abonne, date_creation_du_compte, role, id_adresse, bibliotheque) VALUES (1, 'Dujardin', 'Jean', 'Jeannot', 'dujardin.jean@gmail.com', '1234', '456132245', '2018-12-14', 1, 5, '18004625200177');


--
-- TOC entry 2966 (class 0 OID 42831)
-- Dependencies: 211
-- Data for Name: auteur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (1, 'Hugo', 'Victor', '1802-02-26', '1885-06-22', 'Victor Hugo est un poète, dramaturge, écrivain, romancier et dessinateur romantique français, né le 7 ventôse an X (26 février 1802) à Besançon et mort le 22 mai 1885 à Paris. Il est considéré comme l''un des plus importants écrivains de langue française. Il est aussi une personnalité politique et un intellectuel engagé qui a eu un rôle idéologique majeur et occupe une place marquante dans l''histoire des lettres françaises au XIXe siècle, dans des genres et des domaines d’une remarquable variété.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (2, 'Saint-Exupery', 'Antoine', '1900-06-29', '1944-07-31', 'Antoine de Saint-Exupéry, né le 29 juin 1900 à Lyon et disparu en vol le 31 juillet 1944 au large de Marseille, est un écrivain, poète, aviateur et reporter français.');


--
-- TOC entry 2958 (class 0 OID 42787)
-- Dependencies: 203
-- Data for Name: editeur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES ('1', 'Gallimard');


--
-- TOC entry 2978 (class 0 OID 42902)
-- Dependencies: 223
-- Data for Name: employe; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employe (id_employe, nom, prenom, matricule, date_embauche, date_depart, email, mot_de_passe, bibliotheque, role, id_adresse) VALUES (1, 'Dupont', 'Régis', '168954632', '2017-12-15', NULL, 'dupont.regis@yahoo.fr', '55568985', '18004625200177', 5, 4);


--
-- TOC entry 2970 (class 0 OID 42853)
-- Dependencies: 215
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.genre (id_genre, nom, description) VALUES (1, 'Conte philosophique', 'Le conte philosophique, genre littéraire né au XVIIIe siècle, est une histoire fictive, critique de la société et du pouvoir en place pour transmettre des idées, concepts à portée philosophique : mœurs de la noblesse, régimes politiques, fanatisme religieux ou encore certains courants philosophiques. Il reprend la construction du conte et utilise certaines de ses formulations comme « il était une fois », dans le but de se soustraire à la censure qui sévit à cette époque. Il appartient, comme lui, au genre de l''apologue, court récit allégorique et argumentatif dont on tire une morale, et qui regroupe aussi, entre autres, la fable et l''utopie.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (2, 'Roman', 'Le roman est un genre littéraire caractérisé essentiellement par une narration fictionnelle et dont la première apparition peut être datée du XIIe siècle. Initialement écrit en vers qui jouent sur les assonances, il est écrit en prose dès le XIIe siècle et se distingue du conte ou de l''épopée par sa vocation à être lu individuellement et non écouté.');


--
-- TOC entry 2960 (class 0 OID 42798)
-- Dependencies: 205
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, id_editeur, nombre_de_pages, petite_photo_couverture, grande_photo_couverture) VALUES (1, 'Le Petit Prince', '« Le premier soir je me suis donc endormi sur le sable à mille milles de toute terre habitée. J''étais bien plus isolé qu''un naufragé sur un radeau au milieu de l''Océan. Alors vous imaginez ma surprise, au lever du jour, quand une drôle de petite voix m''a réveillé. Elle disait :
- S''il vous plaît... dessine-moi un mouton !
- Hein !
- Dessine-moi un mouton...
J''ai sauté sur mes pieds comme si j''avais été frappé par la foudre. J''ai bien frotté mes yeux. J''ai bien regardé.
Et j''ai vu un petit bonhomme tout à fait extraordinaire qui me considérait gravement. » ', '2007-03-15', '978-2-070-61275-8', 'Français', '1', 113, 'photo1', 'photo2');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, id_editeur, nombre_de_pages, petite_photo_couverture, grande_photo_couverture) VALUES (2, 'Les Misérables', 'Jean Valjean, les Thénardier, Cosette sont entrés au panthéon de la littérature. Porté par ses personnages, Victor Hugo a écrit avec les Misérables un hymne à la littérature, profondément ancré dans la mentalité collective de son temps. Une épopée du peuple aux prises avec l''Histoire, pleine debruits et de fureur.', '2008-08-22', '978-2-035-83425-6', 'Français', '1', 270, 'photo1', 'photo2');


--
-- TOC entry 2972 (class 0 OID 42864)
-- Dependencies: 217
-- Data for Name: liste_genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (1, 1, 1);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (2, 2, 2);


--
-- TOC entry 2968 (class 0 OID 42842)
-- Dependencies: 213
-- Data for Name: livre_auteurs; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (1, 1, 2);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (2, 2, 1);


--
-- TOC entry 2962 (class 0 OID 42809)
-- Dependencies: 207
-- Data for Name: ouvrage; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre) VALUES (1, 'AEZCON1355', 1);
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre) VALUES (2, 'AEZROM565', 2);


--
-- TOC entry 2964 (class 0 OID 42820)
-- Dependencies: 209
-- Data for Name: pret; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage) VALUES (1, '2020-12-04', NULL, false, 1);
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage) VALUES (2, '2020-12-04', NULL, false, 2);


--
-- TOC entry 2982 (class 0 OID 42924)
-- Dependencies: 227
-- Data for Name: pret_abonne; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pret_abonne (id_pret_abonne, id_pret, id_abonne) VALUES (1, 1, 1);
INSERT INTO public.pret_abonne (id_pret_abonne, id_pret, id_abonne) VALUES (2, 2, 1);


--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 224
-- Name: abonne_id_abonne_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.abonne_id_abonne_seq', 1, false);


--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 219
-- Name: adresse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adresse_id_seq', 1, true);


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 210
-- Name: auteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auteur_id_seq', 1, false);


--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 202
-- Name: editeur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.editeur_id_seq', 1, false);


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 222
-- Name: employe_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employe_id_employe_seq', 1, false);


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 214
-- Name: genre_nom_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genre_nom_seq', 1, false);


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 216
-- Name: liste_genre_id_liste_genre_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.liste_genre_id_liste_genre_seq', 1, false);


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 212
-- Name: livre_auteurs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_auteurs_id_seq', 1, false);


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 204
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_id_seq', 1, false);


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 206
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ouvrage_id_ouvrage_seq', 1, false);


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 226
-- Name: pret_abonne_id_pret_abonne_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pret_abonne_id_pret_abonne_seq', 1, false);


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 208
-- Name: pret_id_pret_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pret_id_pret_seq', 1, false);


-- Completed on 2020-12-13 09:12:19

--
-- PostgreSQL database dump complete
--

