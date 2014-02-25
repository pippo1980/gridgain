// @java.file.header

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.examples.compute;

import org.gridgain.grid.*;
import org.gridgain.grid.lang.*;

/**
 * Demonstrates new functional APIs.
 * <p>
 * Remote nodes should always be started with special configuration file which
 * enables P2P class loading: {@code 'ggstart.{sh|bat} examples/config/example-default.xml'}.
 * <p>
 * Alternatively you can run {@link ComputeNodeStartup} in another JVM which will start GridGain node
 * with {@code examples/config/example-default.xml} configuration.
 *
 * @author @java.author
 * @version @java.version
 */
public class ComputeProjectionExample {
    /**
     * Executes broadcasting message example with closures.
     *
     * @param args Command line arguments, none required but if provided
     *      first one should point to the Spring XML configuration file. See
     *      {@code "examples/config/"} for configuration file examples.
     * @throws GridException If example execution failed.
     */
    public static void main(String[] args) throws Exception {
        try (Grid grid = GridGain.start("examples/config/example-default.xml")) {
            // Say hello to all nodes in the grid, including local node.
            // Note, that Grid itself also implements GridProjection.
            sayHello(grid);

            // Say hello to all remote nodes.
            sayHello(grid.forRemotes());

            // Pick random node out of remote nodes.
            GridProjection randomNode = grid.forRemotes().forRandom();

            // Say hello to a random node.
            sayHello(randomNode);

            // Say hello to all nodes residing on the same host with random node.
            sayHello(grid.forHost(randomNode.node()));
        }
    }

    /**
     * Print 'Hello' message on remote grid nodes.
     *
     * @param g Grid instance.
     * @throws GridException If failed.
     */
    private static void sayHello(final GridProjection g) throws GridException {
        // Print out hello message on all projection nodes.
        g.forRemotes().compute().broadcast(
            new GridRunnable() {
                @Override public void run() {
                    // Print ID of remote node on remote node.
                    System.out.println(">>> Hello Node: " + g.grid().localNode().id());
                }
            }
        ).get();
    }
}