-- REPRESENTS APPLICATION USER
CREATE TABLE "user"
(
    id            SERIAL PRIMARY KEY,
    email         VARCHAR(36) NOT NULL UNIQUE,
    name          VARCHAR(36) NOT NULL,
    activity_rate INTEGER DEFAULT 0,
    password      VARCHAR(64) NOT NULL,
    photo_url     VARCHAR(64)
);

-- DEFINES AUCTION STATE
CREATE TABLE "auction_state"
(
    name VARCHAR(12) PRIMARY KEY NOT NULL UNIQUE
);

INSERT INTO "auction_state"
VALUES ('PREPARING'),
       ('OPEN'),
       ('CLOSED');

-- DEDICATED TO AUCTION ITSELF
CREATE TABLE "auction"
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(64)   NOT NULL,
    description VARCHAR(256)  NOT NULL,
    start_price NUMERIC(6, 2) NOT NULL,
    start_time  TIMESTAMP     NOT NULL,

    state       VARCHAR(12) REFERENCES auction_state (name) ON DELETE CASCADE,
    owner_id    INTEGER REFERENCES "user" (id) ON DELETE CASCADE
);

-- STORES AUCTION PHOTOS
CREATE TABLE "auction_photos"
(
    id                SERIAL PRIMARY KEY,
    image_path        VARCHAR(128) NOT NULL,
    photo_description VARCHAR(36)  NOT NULL,

    auction_id        INTEGER REFERENCES auction (id) ON DELETE CASCADE
);

-- CONNECTED WITH BETS, THAT WERE MADE BY USERS
CREATE TABLE "user_bets"
(
    bet_id     SERIAL PRIMARY KEY,
    bid        NUMERIC(6, 2),
    bet_time   TIMESTAMP DEFAULT NOW(),
    auction_id INTEGER REFERENCES auction (id) ON DELETE CASCADE,
    user_id    INTEGER REFERENCES "user" (id) ON DELETE CASCADE
);

-- DEDICATED TO CHAT (BUT THIS PART IS NOT IMPLEMENTED YET)
CREATE TABLE "message"
(
    id         SERIAL PRIMARY KEY,
    text       VARCHAR(64) NOT NULL,
    send_time  TIMESTAMP DEFAULT NOW(),

    user_id    INTEGER REFERENCES "user" (id) ON DELETE CASCADE,
    auction_id INTEGER REFERENCES "auction" (id) ON DELETE CASCADE
);