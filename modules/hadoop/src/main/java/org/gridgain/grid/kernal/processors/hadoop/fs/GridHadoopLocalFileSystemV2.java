/* 
 Copyright (C) GridGain Systems. All Rights Reserved.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.processors.hadoop.fs;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.local.*;

import java.io.*;
import java.net.*;

import static org.apache.hadoop.fs.FsConstants.*;

/**
 * Local file system replacement for Hadoop jobs.
 */
public class GridHadoopLocalFileSystemV2 extends ChecksumFs {
    /**
     * Creates new local file system.
     *
     * @param cfg Configuration.
     * @throws IOException If failed.
     * @throws URISyntaxException If failed.
     */
    public GridHadoopLocalFileSystemV2(Configuration cfg) throws IOException, URISyntaxException {
        super(new DelegateFS(cfg));
    }

    /**
     * Creates new local file system.
     *
     * @param uri URI.
     * @param cfg Configuration.
     * @throws IOException If failed.
     * @throws URISyntaxException If failed.
     */
    public GridHadoopLocalFileSystemV2(URI uri, Configuration cfg) throws IOException, URISyntaxException {
        this(cfg);
    }

    /**
     * Delegate file system.
     */
    private static class DelegateFS extends DelegateToFileSystem {
        /**
         * Creates new local file system.
         *
         * @param cfg Configuration.
         * @throws IOException If failed.
         * @throws URISyntaxException If failed.
         */
        public DelegateFS(Configuration cfg) throws IOException, URISyntaxException {
            super(LOCAL_FS_URI, new GridHadoopRawLocalFileSystem(), cfg, LOCAL_FS_URI.getScheme(), false);
        }

        /** {@inheritDoc} */
        @Override public int getUriDefaultPort() {
            return -1;
        }

        /** {@inheritDoc} */
        @Override public FsServerDefaults getServerDefaults() throws IOException {
            return LocalConfigKeys.getServerDefaults();
        }

        /** {@inheritDoc} */
        @Override public boolean isValidName(String src) {
            return true;
        }
    }
}
