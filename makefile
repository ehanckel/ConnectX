default: cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java \
cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java \
cpsc2150/extendedConnectX/models/GameBoardMem.java cpsc2150/extendedConnectX/models/IGameBoard.java
	javac cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java \
	cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java \
	cpsc2150/extendedConnectX/models/GameBoardMem.java cpsc2150/extendedConnectX/models/IGameBoard.java 
run:  cpsc2150/extendedConnects/GameScreen.class
	java cpsc2150.extendedConnects.GameScreen

test: cpsc2150/extendedConnectX/tests/TestGameBoard.java cpsc2150/extendedConnectX/tests/TestGameBoardMem.java
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedConnectX/tests/TestGameBoard.java \
	cpsc2150/extendedConnectX/tests/TestGameBoardMem.java

testGB: cpsc2150/extendedConnectX/tests/TestGameBoard.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.tests.TestGameBoard

testGBmem: cpsc2150/extendedConnectX/tests/TestGameBoardMem.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.tests.TestGameBoardMem

clean:
	rm -f cpsc2150/extendedConnectX/tests/*.class
	rm -f cpsc2150/extendedConnects/*.class
	rm -f cpsc2150/extendedConnectX/models/*.class
