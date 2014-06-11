/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.testsuites.bamboo;

import junit.framework.*;
import org.gridgain.grid.kernal.processors.cache.*;

/**
 * JTA integration tests.
 */
public class GridJtaTestSuite extends TestSuite {
    /**
     * @return Test suite.
     * @throws Exception Thrown in case of the failure.
     */
    public static TestSuite suite() throws Exception {
        TestSuite suite = new TestSuite("JTA Integration Test Suite");

        suite.addTestSuite(GridCacheJtaSelfTest.class);
        suite.addTestSuite(GridCacheReplicatedJtaSelfTest.class);
        suite.addTestSuite(GridTmLookupLifecycleAwareSelfTest.class);
        suite.addTestSuite(GridCacheJtaConfigurationValidationSelfTest.class);

        return suite;
    }
}