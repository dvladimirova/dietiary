# add table groups and nutrition_facts

# --- !Ups

CREATE TABLE groups
(
   id serial, 
   name character varying(200) NOT NULL, 
   CONSTRAINT pk_groups_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE groups
  OWNER TO postgres;
  
CREATE TABLE nutrition_facts
(
  id serial NOT NULL,
  name character varying(500) NOT NULL,
  water_g numeric,
  energy_kcal numeric NOT NULL,
  protein_g numeric,
  fat_g numeric,
  carbs_g numeric,
  fiber_g numeric,
  sugars_g numeric,
  minerals json,
  vitamins json,
  lipids json,
  group_id bigint,
  CONSTRAINT pk_nutrition_facts_id PRIMARY KEY (id),
  CONSTRAINT fk_nutrition_group_id FOREIGN KEY (group_id)
      REFERENCES groups (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE nutrition_facts
  OWNER TO postgres;

# --- !Downs

drop table if exists nutrition_facts;
drop table if exists groups;

