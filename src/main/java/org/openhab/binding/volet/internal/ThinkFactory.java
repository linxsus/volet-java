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

import static org.openhab.binding.volet.internal.ThinkFactoryConstants.*;

import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.volet.internal.arduino.Arduino;
import org.openhab.binding.volet.internal.volet.Volet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link ThinkFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author xavier gouraud - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.XGdomotique", service = ThingHandlerFactory.class)
public class ThinkFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = SUPPORTED_DEVICE_THING_TYPES;
    private final Logger logger = LoggerFactory.getLogger(ThinkFactory.class);
    // private final Map<ThingUID, ServiceRegistration<?>> discoveryServiceRegs = new HashMap<>();

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        if (THING_TYPE_ARDUINO.equals(thing.getThingTypeUID())) {
            Arduino handler = new Arduino((Bridge) thing);

            // registerDeviceDiscoveryService(handler);
            return handler;
        } else if (SUPPORTED_THING_TYPES_UIDS.contains(thing.getThingTypeUID())) {
            Volet handler = new Volet(thing);
            return handler;
        } else {
            logger.error("Unsupported thing {}.", thing.getThingTypeUID());
            return null;
        }
    }

    // /**
    // * Registers the device discovery service.
    // *
    // * @param bridgeHandler
    // */
    // private synchronized void registerDeviceDiscoveryService(VoletBridgeHandler bridgeHandler) {
    // // InnogyDeviceDiscoveryService discoveryService = new InnogyDeviceDiscoveryService(bridgeHandler);
    // // this.discoveryServiceRegs.put(bridgeHandler.getThing().getUID(),
    // // bundleContext.registerService(DiscoveryService.class.getName(), discoveryService, new Hashtable<>()));
    // }

}
