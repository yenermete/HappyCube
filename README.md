# HappyCube

This console application tries to solve the happy cube puzzle iwth varying tile length. Default is five and can be changed at https://github.com/yenermete/HappyCube/blob/master/src/main/java/com/cube/constants/CubeConstants.java#L7.

For more information about the puzzle, you can check http://www.happycube.com/. The solution is based on finding a cube pout of six pieces. If a cube is found, it is printed to a file. If not, a message is printed which states that there is no possible cube out of the given pieces.

This program will run until all combinations of pieces are tried or a valid cube is found. Cube validity is tested via bit operations. If all possible cubes are required, the relevant loop should not be terminated and store all cube representations in a list.

To build, run the below command.

```mvn clean install```

To run, execute java -jar <jar_name> <folder_to_write_the_file>
