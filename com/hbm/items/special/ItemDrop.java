package com.hbm.items.special;

import java.util.List;

import org.apache.logging.log4j.Level;

import com.hbm.entity.effect.EntityBlackHole;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.effect.EntityRagingVortex;
import com.hbm.entity.effect.EntityVortex;
import com.hbm.entity.logic.EntityNukeExplosionAdvanced;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.interfaces.IBomb;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemDrop extends Item {

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (entityItem != null) {
			
			ItemStack stack = entityItem.getEntityItem();

			if (stack.getItem() != null && stack.getItem() == ModItems.detonator_deadman) {
				if (!entityItem.worldObj.isRemote) {
					
					if(stack.stackTagCompound != null) {
						
						 int x = stack.stackTagCompound.getInteger("x");
						 int y = stack.stackTagCompound.getInteger("y");
						 int z = stack.stackTagCompound.getInteger("z");
						 
						 if(entityItem.worldObj.getBlock(x, y, z) instanceof IBomb)
						 {
							if(!entityItem.worldObj.isRemote)
							{
								((IBomb)entityItem.worldObj.getBlock(x, y, z)).explode(entityItem.worldObj, x, y, z);

					    		if(MainRegistry.enableExtendedLogging)
					    			MainRegistry.logger.log(Level.INFO, "[DET] Tried to detonate block at " + x + " / " + y + " / " + z + " by dead man's switch!");
							}
						 }
					}

					entityItem.worldObj.createExplosion(entityItem, entityItem.posX, entityItem.posY,
							entityItem.posZ, 0.0F, true);
					entityItem.setDead();
				}
			}
			if (stack.getItem() != null && stack.getItem() == ModItems.detonator_de) {
				if (!entityItem.worldObj.isRemote) {
					entityItem.worldObj.createExplosion(entityItem, entityItem.posX, entityItem.posY,
							entityItem.posZ, 15.0F, true);

		    		if(MainRegistry.enableExtendedLogging)
		    			MainRegistry.logger.log(Level.INFO, "[DET] Detonated dead man's explosive at " + ((int)entityItem.posX) + " / " + ((int)entityItem.posY) + " / " + ((int)entityItem.posZ) + "!");
				}
				
				entityItem.setDead();
			}
			
			if (entityItem.onGround) {

				if (stack.getItem() != null && stack.getItem() == ModItems.cell_antimatter) {
					if (!entityItem.worldObj.isRemote) {
						entityItem.worldObj.createExplosion(entityItem, entityItem.posX, entityItem.posY,
								entityItem.posZ, 10.0F, true);
					}
				}
				if (stack.getItem() != null && stack.getItem() == ModItems.pellet_antimatter) {
					if (!entityItem.worldObj.isRemote) {
						ExplosionLarge.explodeFire(entityItem.worldObj, entityItem.posX, entityItem.posY,
								entityItem.posZ, 100, true, true, true);
					}
				}
				if (stack.getItem() != null && stack.getItem() == ModItems.cell_anti_schrabidium) {
					if (!entityItem.worldObj.isRemote) {
						entityItem.worldObj.playSoundEffect(entityItem.posX, entityItem.posY, entityItem.posZ,
								"random.explode", 100.0f, entityItem.worldObj.rand.nextFloat() * 0.1F + 0.9F);

						EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(entityItem.worldObj);
						entity.posX = entityItem.posX;
						entity.posY = entityItem.posY;
						entity.posZ = entityItem.posZ;
						entity.destructionRange = MainRegistry.aSchrabRadius;
						entity.speed = 25;
						entity.coefficient = 1.0F;
						entity.waste = false;

						entityItem.worldObj.spawnEntityInWorld(entity);
			    		
			    		EntityCloudFleija cloud = new EntityCloudFleija(entityItem.worldObj, MainRegistry.aSchrabRadius);
			    		cloud.posX = entityItem.posX;
			    		cloud.posY = entityItem.posY;
			    		cloud.posZ = entityItem.posZ;
			    		entityItem.worldObj.spawnEntityInWorld(cloud);
					}
				}
				if (stack.getItem() != null && stack.getItem() == ModItems.singularity) {
					if (!entityItem.worldObj.isRemote) {

			        	EntityVortex bl = new EntityVortex(entityItem.worldObj, 1.5F);
			        	bl.posX = entityItem.posX ;
			        	bl.posY = entityItem.posY ;
			        	bl.posZ = entityItem.posZ ;
			        	entityItem.worldObj.spawnEntityInWorld(bl);
					}
				}
				if (stack.getItem() != null && stack.getItem() == ModItems.singularity_counter_resonant) {
					if (!entityItem.worldObj.isRemote) {

			        	EntityVortex bl = new EntityVortex(entityItem.worldObj, 2.5F);
			        	bl.posX = entityItem.posX ;
			        	bl.posY = entityItem.posY ;
			        	bl.posZ = entityItem.posZ ;
			        	entityItem.worldObj.spawnEntityInWorld(bl);
					}
				}
				if (stack.getItem() != null && stack.getItem() == ModItems.singularity_super_heated) {
					if (!entityItem.worldObj.isRemote) {

			        	EntityVortex bl = new EntityVortex(entityItem.worldObj, 2.5F);
			        	bl.posX = entityItem.posX ;
			        	bl.posY = entityItem.posY ;
			        	bl.posZ = entityItem.posZ ;
			        	entityItem.worldObj.spawnEntityInWorld(bl);
					}
				}
				if (stack.getItem() != null && stack.getItem() == ModItems.black_hole) {
					if (!entityItem.worldObj.isRemote) {
						/*entityItem.worldObj.playSoundEffect(entityItem.posX, entityItem.posY, entityItem.posZ,
								"random.explode", 100.0f, entityItem.worldObj.rand.nextFloat() * 0.1F + 0.9F);

						EntityNukeExplosionAdvanced entity = new EntityNukeExplosionAdvanced(entityItem.worldObj);
						entity.posX = entityItem.posX;
						entity.posY = entityItem.posY;
						entity.posZ = entityItem.posZ;
						entity.destructionRange = MainRegistry.aSchrabRadius * 3;
						entity.speed = 25;
						entity.coefficient = 0.01F;
						entity.coefficient2 = 0.01F;
						entity.waste = false;

						entityItem.worldObj.spawnEntityInWorld(entity);*/

			        	EntityBlackHole bl = new EntityBlackHole(entityItem.worldObj, 1.5F);
			        	bl.posX = entityItem.posX ;
			        	bl.posY = entityItem.posY ;
			        	bl.posZ = entityItem.posZ ;
			        	entityItem.worldObj.spawnEntityInWorld(bl);
					}
				}
				if (stack.getItem() != null && stack.getItem() == ModItems.singularity_spark) {
					if (!entityItem.worldObj.isRemote) {
			        	EntityRagingVortex bl = new EntityRagingVortex(entityItem.worldObj, 3.5F);
			        	bl.posX = entityItem.posX ;
			        	bl.posY = entityItem.posY ;
			        	bl.posZ = entityItem.posZ ;
			        	entityItem.worldObj.spawnEntityInWorld(bl);
					}
				}
				if (stack.getItem() != null && stack.getItem() == ModItems.crystal_xen) {
					if (!entityItem.worldObj.isRemote) {
						ExplosionChaos.floater(entityItem.worldObj, (int)entityItem.posX, (int)entityItem.posY, (int)entityItem.posZ, 25, 75);
						ExplosionChaos.move(entityItem.worldObj, (int)entityItem.posX, (int)entityItem.posY, (int)entityItem.posZ, 25, 0, 75, 0);
					}
				}

				entityItem.setDead();
				return true;
			}
		}
		return false;
    }
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool)
	{
		if (this == ModItems.cell_antimatter) {
			list.add("Warning: Exposure to matter will");
			list.add("lead to violent annihilation!");
		}
		if (this == ModItems.pellet_antimatter) {
			list.add("Very heavy antimatter cluster.");
			list.add("Gets rid of black holes.");
		}
		if (this == ModItems.cell_anti_schrabidium) {
			list.add("Warning: Exposure to matter will");
			list.add("create a fólkvangr field!");
		}
		if (this == ModItems.singularity) {
			list.add("You may be asking:");
			list.add("\"But HBM, a manifold with an undefined");
			list.add("state of spacetime? How is this possible?\"");
			list.add("Long answer short:");
			list.add("\"I have no idea!\"");
		}
		if (this == ModItems.singularity_counter_resonant) {
			list.add("Nullifies resonance of objects in");
			list.add("non-euclidean space, creates variable");
			list.add("gravity well. Spontaneously spawns");
			list.add("tesseracts. If a tesseract happens to");
			list.add("appear near you, do not look directly");
			list.add("at it.");
		}
		if (this == ModItems.singularity_super_heated) {
			list.add("Continuously heats up matter by");
			list.add("resonating every planck second.");
			list.add("Tends to catch fire or to create");
			list.add("small plamsa arcs. Not edible.");
		}
		if (this == ModItems.black_hole) {
			list.add("Contains a regular singularity");
			list.add("in the center. Large enough to");
			list.add("stay stable. It's not the end");
			list.add("of the world as we know it,");
			list.add("and I don't feel fine.");
		}
		if (this == ModItems.detonator_deadman) {
			list.add("Shift right-click to set position,");
			list.add("drop to detonate!");
			if(itemstack.getTagCompound() == null)
			{
				list.add("No position set!");
			} else {
				list.add("Set pos to " + itemstack.stackTagCompound.getInteger("x") + ", " + itemstack.stackTagCompound.getInteger("y") + ", " + itemstack.stackTagCompound.getInteger("z"));
			}
		}
		if (this == ModItems.detonator_de) {
			list.add("Explodes when dropped!");
		}
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		if(this != ModItems.detonator_deadman) {
			return super.onItemUse(stack, player, world, x, y, z, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
		}
		
		if(stack.stackTagCompound == null)
		{
			stack.stackTagCompound = new NBTTagCompound();
		}
		
		if(player.isSneaking())
		{
			stack.stackTagCompound.setInteger("x", x);
			stack.stackTagCompound.setInteger("y", y);
			stack.stackTagCompound.setInteger("z", z);
			
			if(world.isRemote)
			{
				player.addChatMessage(new ChatComponentText("Position set!"));
			}
			
	        world.playSoundAtEntity(player, "hbm:item.techBoop", 2.0F, 1.0F);
        	
			return true;
		}
		
		return false;
    }

}
