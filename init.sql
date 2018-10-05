--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5 (Debian 10.5-1.pgdg90+1)
-- Dumped by pg_dump version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: agreements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agreements (
    id bigint NOT NULL,
    active boolean NOT NULL,
    amount numeric(12,2),
    amount_period character varying(10),
    amount_type character varying(6),
    end_date date,
    order_number character varying(255),
    start_date date,
    system_id bigint NOT NULL
);


ALTER TABLE public.agreements OWNER TO postgres;

--
-- Name: agreements_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.agreements_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.agreements_id_seq OWNER TO postgres;

--
-- Name: agreements_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.agreements_id_seq OWNED BY public.agreements.id;


--
-- Name: systems; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.systems (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(25) NOT NULL,
    owner character varying(25) NOT NULL,
    tech_description character varying(255)
);


ALTER TABLE public.systems OWNER TO postgres;

--
-- Name: systems_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.systems_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.systems_id_seq OWNER TO postgres;

--
-- Name: systems_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.systems_id_seq OWNED BY public.systems.id;


--
-- Name: agreements id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agreements ALTER COLUMN id SET DEFAULT nextval('public.agreements_id_seq'::regclass);


--
-- Name: systems id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.systems ALTER COLUMN id SET DEFAULT nextval('public.systems_id_seq'::regclass);


--
-- Data for Name: agreements; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agreements (id, active, amount, amount_period, amount_type, end_date, order_number, start_date, system_id) FROM stdin;
\.


--
-- Data for Name: systems; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.systems (id, description, name, owner, tech_description) FROM stdin;
1	Zarządzanie stadniną koni	KUCYK	Sebastian Koń	Java, Spring, Hibernate, Maven
2	Zarządzanie magazynem kajaków	ŁÓDKA	Sławomir Kajak	NodeJs, Angular
6	Forum na temat gadów	ŻÓŁWIK	Mirosław Powolny	PHP
5	Blog o grzybach	DEMON	Anna Szatan	WordPress
4	Wypożyczalnia kotów	KOTEK	Jarosław Kot	WordPress
3	Blog o militariach	KAPISZON	Waldemar Kapiszon	Java, Spring
7	Strona schroniska dla zwierząt	KOJOTEK	Marian Kojotek	WordPress
\.


--
-- Name: agreements_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.agreements_id_seq', 1, false);


--
-- Name: systems_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.systems_id_seq', 7, true);


--
-- Name: agreements agreements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agreements
    ADD CONSTRAINT agreements_pkey PRIMARY KEY (id);


--
-- Name: systems systems_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.systems
    ADD CONSTRAINT systems_pkey PRIMARY KEY (id);


--
-- Name: systems uk_csju7iu71w27t5020jc47ps2e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.systems
    ADD CONSTRAINT uk_csju7iu71w27t5020jc47ps2e UNIQUE (name);


--
-- Name: agreements uk_gff3h5v7muxhngqxyky88wa90; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agreements
    ADD CONSTRAINT uk_gff3h5v7muxhngqxyky88wa90 UNIQUE (order_number);


--
-- Name: agreements fk1bqdo8c71hfc0e11t8jpoxwvl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agreements
    ADD CONSTRAINT fk1bqdo8c71hfc0e11t8jpoxwvl FOREIGN KEY (system_id) REFERENCES public.systems(id);


--
-- PostgreSQL database dump complete
--

