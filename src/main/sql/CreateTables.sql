Create Table if not exists MagicDB.All_Cards (
  multiverse_id int not null primary key,
  name varchar( 255 ) not null,
  color varchar( 10 ) not null,
  cost varchar( 80 ) not null,
  converted_cost int null,
  types varchar( 80 ) not null,
  text varchar( 2048 ) null,
  flavor_text varchar( 2048 ) null,
  power varchar( 80 ) null,
  toughness varchar( 80 ) null,
  expansion varchar( 1024 ) not null,
  rarity varchar( 80 ) null,
  artist varchar( 1024 ) null,
  image longblob,
  number varchar( 80 ) null,
  watermark varchar( 1024 ) null,
  language varchar( 80 ) null,
  created timestamp not null DEFAULT 0,
  data_updated timestamp not null DEFAULT now() ON UPDATE now(),
  touched_by_updater timestamp not null DEFAULT 0,
  KEY name (name),
  KEY color (color),
  KEY types (types),
  KEY language (language),
  KEY created (created),
  KEY touched_by_updater (touched_by_updater),
  KEY data_updated (data_updated)
);

DROP TRIGGER IF EXISTS MagicDB.allcards_set_created_trigger;
CREATE TRIGGER MagicDB.allcards_set_created_trigger BEFORE INSERT ON MagicDB.All_Cards
    FOR EACH ROW SET NEW.created = now();

Create Table if not exists MagicDB.My_Cards (
  multiverse_id int not null primary key,
  count int not null,
  foreign key (multiverse_id) references All_Cards(multiverse_id)
);

/*  Not sure if I need to track type Type. That is, "Basic" is a SuperType, "Creature" is a Type, and "Human" is a SubType
    Since SuperTypes and Types are hardcoded as Enums, there's no need at this point, but currently ALL Types are stored in this table
    This means that I'm filtering out non-SubTypes in Java after the fact.
*/
Create Table if not exists MagicDB.Card_Types (
  multiverse_id int not null,
  type_name varchar( 80 ) not null,
  created timestamp not null DEFAULT 0,
  updated timestamp not null DEFAULT now() ON UPDATE now(),
  foreign key (multiverse_id) references All_Cards(multiverse_id) on delete cascade,
  key type_name (type_name),
  unique key unique_composite_index (multiverse_id, type_name)
);

DROP TRIGGER IF EXISTS MagicDB.cardtypes_set_created_trigger;
CREATE TRIGGER MagicDB.cardtypes_set_created_trigger BEFORE INSERT ON MagicDB.Card_Types
    FOR EACH ROW SET NEW.created = now();