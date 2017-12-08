/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.ml;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.xpack.ml.calendars.SpecialEvent;
import org.elasticsearch.xpack.ml.job.persistence.ElasticsearchMappings;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public final class MlMetaIndex {
    /**
     * Where to store the ml info in Elasticsearch - must match what's
     * expected by kibana/engineAPI/app/directives/mlLogUsage.js
     */
    public static final String INDEX_NAME = ".ml-meta";

    public static final String TYPE = "doc";

    private MlMetaIndex() {}

    public static XContentBuilder docMapping() throws IOException {
        XContentBuilder builder = jsonBuilder();
        builder.startObject();
            builder.startObject(TYPE);
                ElasticsearchMappings.addDefaultMapping(builder);
                builder.startObject(ElasticsearchMappings.PROPERTIES)
                    .startObject(SpecialEvent.START_TIME.getPreferredName())
                        .field(ElasticsearchMappings.TYPE, ElasticsearchMappings.DATE)
                    .endObject()
                    .startObject(SpecialEvent.END_TIME.getPreferredName())
                        .field(ElasticsearchMappings.TYPE, ElasticsearchMappings.DATE)
                    .endObject()
                .endObject()
            .endObject()
        .endObject();
        return builder;
    }
}
