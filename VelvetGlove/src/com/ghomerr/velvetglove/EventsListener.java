package com.ghomerr.velvetglove;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EventsListener implements Listener
{
	public static final String PERMISSION_USER = "velvetglove.user";

	public VelvetGlove _plugin = null;

	public EventsListener (final VelvetGlove plugin)
	{
		this._plugin = plugin;
		this._plugin.getServer().getPluginManager().registerEvents(this, this._plugin);
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(final EntityDamageByEntityEvent event)
	{
		if (_plugin.isPluginEnabled())
		{
			final EntityType type = event.getEntityType();
			if (type == EntityType.PLAYER && _plugin.isPlayersTargeted() || type != EntityType.PLAYER && _plugin.isOthersTargeted())
			{
				final Entity damager = event.getDamager();
				if (event.getDamager().getType() == EntityType.PLAYER)
				{
					final Player player = (Player) damager;
					if (player.hasPermission(PERMISSION_USER))
					{
						final ItemStack itemInHand = player.getItemInHand();
						if (itemInHand != null && itemInHand.getType() == _plugin.getVelvetGloveMaterial())
						{
							event.setDamage(0);
						}
					}
				}
			}
		}
	}
}
