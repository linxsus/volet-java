package org.openhab.binding.volet.internal.arduino;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Arduino extends BaseBridgeHandler {

    @Nullable
    private ArduinoConfiguration config;

    private FactorySerie factory;

    private final Logger logger = LoggerFactory.getLogger(Arduino.class);

    public Arduino(Bridge bridge) {
        super(bridge);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initialize() {
        config = getConfigAs(ArduinoConfiguration.class);
        logger.debug("creation d'une liaison arduino " + config.portCom);
        factory = new FactorySerie(9600, "COM3");
        // TODO creation de la connexion
        updateStatus(ThingStatus.ONLINE);
    }

    public String getPortCom() {
        return config.portCom;
    }
}
