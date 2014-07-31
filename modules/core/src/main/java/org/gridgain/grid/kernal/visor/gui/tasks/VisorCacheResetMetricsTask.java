/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.visor.gui.tasks;

import org.gridgain.grid.*;
import org.gridgain.grid.cache.*;
import org.gridgain.grid.kernal.processors.task.*;
import org.gridgain.grid.kernal.visor.cmd.*;
import org.gridgain.grid.util.typedef.internal.*;

/**
 * Reset compute grid metrics.
 */
@GridInternal
public class VisorCacheResetMetricsTask extends VisorOneNodeTask<String, Void> {
    /** */
    private static final long serialVersionUID = 0L;

    /** {@inheritDoc} */
    @Override protected VisorCacheResetMetricsJob job(String arg) {
        return new VisorCacheResetMetricsJob(arg);
    }

    /**
     * Job that reset cache metrics.
     */
    private static class VisorCacheResetMetricsJob extends VisorJob<String, Void> {
        /** */
        private static final long serialVersionUID = 0L;

        /**
         * @param arg Cache name to reset metrics for.
         */
        private VisorCacheResetMetricsJob(String arg) {
            super(arg);
        }

        /** {@inheritDoc} */
        @Override protected Void run(String cacheName) throws GridException {
            GridCache cache = g.cachex(cacheName);

            if (cache != null)
                cache.resetMetrics();

            return null;
        }

        /** {@inheritDoc} */
        @Override public String toString() {
            return S.toString(VisorCacheResetMetricsJob.class, this);
        }
    }
}