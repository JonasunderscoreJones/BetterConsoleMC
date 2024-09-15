package me.jonasjones.betterconsolemc.command;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

public class CapturingCommandSource extends ServerCommandSource {
    private final StringBuilder outputBuilder;

    public CapturingCommandSource(MinecraftServer server) {
        super(server,
                new Vec3d(0, 0, 0),  // No specific entity
                new Vec2f(0, 0),  // No specific entity
                server.getCommandSource().getWorld(),
                server.getCommandSource().getServer().getOpPermissionLevel(),
                "Server",
                Text.literal("Server"),
                server,
                null
        );
        this.outputBuilder = new StringBuilder();
    }

    @Override
    public void sendMessage(Text message) {
        outputBuilder.append(message.getString()).append("\n");
    }

    public String getCapturedOutput() {
        return outputBuilder.toString();
    }
}
