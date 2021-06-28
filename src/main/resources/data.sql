INSERT INTO vhs(title, year, plot) VALUES('Return of the Dragon', 1974, 'Tang Lung arrives in Rome to help save the family restaurant from a group of thugs who want the property for themselves.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('The Lion King', 1994, 'Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('Mary Poppins', 1964, 'In turn of the century London, a magical nanny employs music and adventure to help two neglected children become closer to their father.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('The Fifth Element', 1997, 'In the colorful future, a cab driver unwittingly becomes the central figure in the search for a legendary cosmic weapon to keep Evil and Mr. Zorg at bay.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('Bloodsport', 1988, '"Bloodsport" follows Frank Dux, an American martial artist serving in the military, who decides to leave the army to compete in a martial arts tournament in Hong Kong where fights to the death can occur.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('The Parent Trap', 1998, 'Identical twins Annie and Hallie, separated at birth and each raised by one of their biological parents, later discover each other for the first time at summer camp and make a plan to bring their wayward parents back together.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('E.T. the Extra-Terrestrial', 1982, 'A troubled child summons the courage to help a friendly alien escape Earth and return to his home world.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('Batman & Robin', 1997, 'Batman and Robin try to keep their relationship together even as they must stop Mr. Freeze and Poison Ivy from freezing Gotham City.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('The Matrix', 1999, 'When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('Kickboxer', 1989, 'Kurt Sloane must learn the ancient kick boxing art of Muay Thai in order to avenge his brother.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('Toy Story', 1995, 'A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy''s room.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('Shrek', 2001, 'A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back.') ON CONFLICT DO NOTHING;;
INSERT INTO vhs(title, year, plot) VALUES('Harry Potter and the Prisoner of Azkaban', 2004, 'Harry Potter, Ron and Hermione return to Hogwarts School of Witchcraft and Wizardry for their third year of study, where they delve into the mystery surrounding an escaped prisoner who poses a dangerous threat to the young wizard.') ON CONFLICT DO NOTHING;;

INSERT INTO vhs_user(username, password, first_name, last_name, role) VALUES('admin', '$2a$10$mIZNpIExaLgb6HCIIjC8buuOBn4YKZqoyRDLPYvOlCbEpYGHmBfbO', 'John', 'Doe', 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO vhs_user(username, password, first_name, last_name, role) VALUES('user1', '$2a$10$noWQ8vRW9XyU0qcOsCm.ruUE4/gqAavB4aHqmnOLGVgfe1syZkPMu', 'Ivan', 'Ivanic', 'USER') ON CONFLICT DO NOTHING;
INSERT INTO vhs_user(username, password, first_name, last_name, role) VALUES('user2', '$2a$10$0tHaLWLq17yhtvalImMeNenW1sAzjynogUQz9z9kKxUiVbvD/raDG', 'Petra', 'Petric', 'USER') ON CONFLICT DO NOTHING;