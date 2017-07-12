INSERT INTO user VALUES ('id-1', 'acc-tok-1', 'emp-id-1', 'knol-sahil', 'sahil.sawhney@knoldus.in', 'knol-password1', 'knol-fullStack1', NOW(), NOW());

INSERT INTO user VALUES ('id-2', 'acc-tok-2', 'emp-id-2', 'knol-joy', 'jyotsana@knoldus.com', 'knol-password2', 'knol-fullStack2', NOW(), NOW());

INSERT INTO asset VALUES ('id-1', 'test asset', 'asset-unique-1', 'projector', false, Now(), Now());

INSERT INTO asset VALUES ('asset-id-2', 'test asset 2', 'asset-unique-2', 'projector', false, Now(), Now());

INSERT INTO asset VALUES ('asset-id-3', 'test asset 3', 'asset-unique-3', 'projector', false, Now(), Now());

INSERT INTO booking VALUES ('id-1', 'user-id-1', 'asset-id-2', 4, 'user rating', 3, 'asset rating', 'booked', 'user-id-2', Now(), Now(), Now(), Now());

INSERT INTO booking VALUES ('id-3', 'user-id-1', 'asset-id-2', 4, 'user rating', 3, 'asset rating', 'booked', 'user-id-2', Now(), '2017-07-08 11:00:00.0', '2017-07-08 12:00:00.0', Now());

INSERT INTO booking VALUES ('id-4', 'user-id-1', 'asset-id-3', 4, 'user rating', 3, 'asset rating', 'cancelled', 'user-id-2', Now(), '2017-07-08 14:00:00.0', '2017-07-08 15:00:00.0', Now());
