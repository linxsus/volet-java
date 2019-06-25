/**
 * Copyright (c) 2014,2019 by the respective copyright holders.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.volet.internal;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link ThinkFactoryConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author xavier gouraud - Initial contribution
 */
@NonNullByDefault
public class ThinkFactoryConstants {

    private static final String BINDING_ID = "XGdomotique";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_ARDUINO = new ThingTypeUID(BINDING_ID, "Arduino");
    public static final ThingTypeUID THING_TYPE_VOLET = new ThingTypeUID(BINDING_ID, "Volet");
    public static final Set<ThingTypeUID> SUPPORTED_DEVICE_THING_TYPES = Collections
            .unmodifiableSet(Stream.of(THING_TYPE_ARDUINO, THING_TYPE_VOLET).collect(Collectors.toSet()));
    // List of all Channel ids
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";
    public static final String STOP = "STOP";
}
