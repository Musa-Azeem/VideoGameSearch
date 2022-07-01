compile: 
	@mkdir -p bin
	@javac src/GenLL.java src/VideoGame.java src/VideoGameFrontEnd.java src/VideoGameHelper.java -d bin
run:
	@java -cp bin VideoGameFrontEnd

clean:
	@rm -rf bin