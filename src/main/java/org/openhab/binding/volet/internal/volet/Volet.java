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
package org.openhab.binding.volet.internal.volet;

import static org.openhab.binding.volet.internal.ThinkFactoryConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.openhab.binding.volet.internal.arduino.Arduino;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Volet} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author xavier gouraud - Initial contribution
 */
@NonNullByDefault
public class Volet extends BaseThingHandler {

    private final Logger logger = LoggerFactory.getLogger(Volet.class);
    private final Object lock = new Object();
    @Nullable
    private Arduino bridgeHandler;

    @Nullable
    private VoletConfiguration config;

    public Volet(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("run volet");
        logger.debug("volet " + channelUID.getId() + " " + command + " " + config.config1);

        if (command instanceof RefreshType) {
            // TODO lecture des l'etat du volet
        }
        if (UP.equals(channelUID.getId()) && command.toString().equals("ON")) {
            updateState(DOWN, OnOffType.OFF);
            updateState(STOP, OnOffType.OFF);
        }
        if (DOWN.equals(channelUID.getId()) && command.toString().equals("ON")) {
            updateState(UP, OnOffType.OFF);
            updateState(STOP, OnOffType.OFF);
        }
        if (STOP.equals(channelUID.getId()) && command.toString().equals("ON")) {
            updateState(DOWN, OnOffType.OFF);
            updateState(UP, OnOffType.OFF);
        }
        // TODO: handle command

        // Note: if communication with thing fails for some reason,
        // indicate that by setting the status with detail information:
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
        // "Could not control device at IP address x.x.x.x");
    }

    @Override
    public void initialize() {
        logger.debug("creation d'un volet");
        config = getConfigAs(VoletConfiguration.class);
        bridgeHandler = getVoletBridgeHandler();
        // TODO: Initialize the handler.
        // The framework requires you to return from this method quickly. Also, before leaving this method a thing
        // status from one of ONLINE, OFFLINE or UNKNOWN must be set. This might already be the real thing status in
        // case you can decide it directly.
        // In case you can not decide the thing status directly (e.g. for long running connection handshake using WAN
        // access or similar) you should set status UNKNOWN here and then decide the real status asynchronously in the
        // background.

        // set the thing status to UNKNOWN temporarily and let the background task decide for the real status.
        // the framework is then able to reuse the resources from the thing handler initialization.
        // we set this upfront to reliably check status updates in unit tests.
        updateStatus(ThingStatus.UNKNOWN);

        // Example for background initialization:
        scheduler.execute(() -> {
            boolean thingReachable = true; // <background task with long running initialization here>
            // when done do:
            if (thingReachable) {
                updateStatus(ThingStatus.ONLINE);
            } else {
                updateStatus(ThingStatus.OFFLINE);
            }
        });

        logger.debug("fin de creation d'un volet");

        // Note: When initialization can NOT be done set the status with more details for further
        // analysis. See also class ThingStatusDetail for all available status details.
        // Add a description to give user information to understand why thing does not work as expected. E.g.
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
        // "Can not access device as username and/or password are invalid");
    }

    /**
     * Returns the volet bridge handler.
     *
     * @return the {@link Arduino} or null
     */
    @Nullable
    private Arduino getVoletBridgeHandler() {
        synchronized (this.lock) {
            if (this.bridgeHandler == null) {
                Bridge bridge = getBridge();
                if (bridge == null) {
                    return null;
                }
                ThingHandler handler = bridge.getHandler();
                if (handler instanceof Arduino) {
                    this.bridgeHandler = (Arduino) handler;
                    // TODO
                    // this.bridgeHandler.registerDeviceStatusListener(this);
                } else {
                    return null;
                }
            }
            return this.bridgeHandler;
        }
    }
}
