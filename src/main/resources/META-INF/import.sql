set search_path = numberquiz;
--
-- Data for Name: tb_problem_sequence; Type: TABLE DATA; Schema: public; Owner: database
--
INSERT INTO tb_problem_sequence (id, problem_sequence, solution) VALUES (-1, '[ 3, 1, 4, 1, 5 ]', 9);
INSERT INTO tb_problem_sequence (id, problem_sequence, solution) VALUES (-2, '[ 1, 1, 2, 3, 5 ]', 8);
INSERT INTO tb_problem_sequence (id, problem_sequence, solution) VALUES (-3, '[ 1, 4, 9, 16, 25 ]', 36);
INSERT INTO tb_problem_sequence (id, problem_sequence, solution) VALUES (-4, '[ 2, 3, 5, 7, 11 ]', 13);
INSERT INTO tb_problem_sequence (id, problem_sequence, solution) VALUES (-5, '[ 1, 2, 4, 8, 16 ]', 32);


--
-- Data for Name: tb_problem; Type: TABLE DATA; Schema: public; Owner: database
--

INSERT INTO tb_problem (id, problem_sequence_id) VALUES (-1, -1);
INSERT INTO tb_problem (id, problem_sequence_id) VALUES (-2, -2);
INSERT INTO tb_problem (id, problem_sequence_id) VALUES (-3, -3);
INSERT INTO tb_problem (id, problem_sequence_id) VALUES (-4, -4);
INSERT INTO tb_problem (id, problem_sequence_id) VALUES (-5, -5);


--
-- Data for Name: tb_user;
--

INSERT INTO tb_user (id, name, password) VALUES (-1, 'joao', 'joao');
INSERT INTO tb_user (id, name, password) VALUES (-2, 'user', 'user');

--
-- Data for Name: tb_quiz;
--

INSERT INTO tb_quiz(id, score, user_id) VALUES (-1, 50, -1);
INSERT INTO tb_quiz(id, score, user_id) VALUES (-2, 2, -1);
INSERT INTO tb_quiz(id, score, user_id) VALUES (-3, 8, -1);
INSERT INTO tb_quiz(id, score, user_id) VALUES (-4, 9, -1);
INSERT INTO tb_quiz(id, score, user_id) VALUES (-5, 59, -2);
INSERT INTO tb_quiz(id, score, user_id) VALUES (-6, 222, -2);

