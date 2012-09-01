package com.ghomerr.velvetglove;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandsExecutor implements CommandExecutor
{
	private static final String ON_CMD = "ON";
	private static final String OFF_CMD = "OFF";
	private static final String TARGETS_CMD = "TARGETS";
	private static final String SET_CMD = "SET";

	private static final String PARAM_PLAYERS = "PLAYERS";
	private static final String PARAM_OTHERS = "OTHERS";

	private static final String PERMISSION_ADMIN = "velvetglove.admin";

	private VelvetGlove _plugin = null;

	public CommandsExecutor (final VelvetGlove plugin)
	{
		this._plugin = plugin;
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] params)
	{
		if (sender.hasPermission(PERMISSION_ADMIN))
		{
			String param1 = "";
			if (params.length > 0)
			{
				param1 = params[0];
			}

			if (ON_CMD.equalsIgnoreCase(param1))
			{
				_plugin.setPluginEnabled(true);
				sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.GREEN + ON_CMD);
			}
			else if (OFF_CMD.equalsIgnoreCase(param1))
			{
				_plugin.setPluginEnabled(false);
				sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.RED + OFF_CMD);
			}
			else if (TARGETS_CMD.equalsIgnoreCase(param1))
			{
				if (params.length > 2)
				{
					final String param2 = params[1];
					final String param3 = params[2];

					if (PARAM_PLAYERS.equalsIgnoreCase(param2))
					{
						_plugin.setPlayersTargeted(param3);
						sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.YELLOW + TARGETS_CMD + " "
								+ PARAM_PLAYERS + " " + _plugin.isPlayersTargeted());
					}
					else if (PARAM_OTHERS.equalsIgnoreCase(param2))
					{
						_plugin.setOthersTargeted(param3);
						sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.YELLOW + TARGETS_CMD + " "
								+ PARAM_OTHERS + " " + _plugin.isOthersTargeted());
					}
					else
					{
						sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.RED
								+ " TARGETS KO ! /vg TARGETS [PLAYERS|OTHERS] [true|false]");
					}
				}
				else
				{
					sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.RED
							+ " TARGETS KO ! /vg TARGETS [PLAYERS|OTHERS] [true|false]");
				}
			}
			else if (SET_CMD.equalsIgnoreCase(param1))
			{
				String param2 = "";
				if (params.length > 1)
				{
					param2 = params[1];
				}

				if (_plugin.setVelvetGloveMaterial(param2))
				{
					sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.GREEN + param2.toUpperCase() + " OK.");
				}
				else
				{
					sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.RED + param2.toUpperCase() + " KO!");
				}
			}
			else
			{
				sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.YELLOW
						+ "Commands: /velvetglove ON, OFF, SET <material>, TARGETS [PLAYERS|OTHERS] [true|false] ");
			}
		}
		else
		{
			sender.sendMessage(ChatColor.DARK_PURPLE + VelvetGlove.VELVET_TAG + ChatColor.RED + "Permissions KO !");
		}

		return true;
	}
}
