package net.minecraft.src;

/*
---------------------------------------------------------------------------------------------------------------------------------------------------

This is a "souped-up" port of Minecraft Infdev 20100415's terrain generator. As much as I like its aesthetic, I must
admit that I felt like it was lacking in certain areas, largely with the lack of caves, flowers, and mushrooms. Despite
liking the big trees, they started to come off as same-y to me after a while, and that might've been due to lack of variety
in the sizes of the trees being generated. To address my nitpicks, I've decided to extend this terrain generator to offer
the features I've just mentioned (alongside more) while retaining the core "feel" of the source material.

The result is a fusion of Minecraft Infdev 20100415's and Minecraft Alpha v1.0.0's world generation.

Hopefully, my additions will help reduce repetition and make the world feel more interesting.

---------------------------------------------------------------------------------------------------------------------------------------------------
*/

import java.util.Random;

public class ChunkProviderGenerate implements IChunkProvider {
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	private NoiseGeneratorOctaves noiseGen5;
	private NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;

	public ChunkProviderGenerate(World var1, long var2) {
		this.worldObj = var1;
		this.rand = new Random(var2);
		new Random(var2);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 4);
		new NoiseGeneratorOctaves(this.rand, 5);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 5);
	}

	public Chunk provideChunk(int var1, int var2) {
		this.rand.setSeed((long)var1 * 341873128712L + (long)var2 * 132897987541L);
		byte[] var3 = new byte[-Short.MIN_VALUE];
		Chunk var4 = new Chunk(this.worldObj, var3, var1, var2);

		int var5;
		int var6;
		double var50;
		for(var5 = 0; var5 < 4; ++var5) {
			for(var6 = 0; var6 < 4; ++var6) {
				double[][] var7 = new double[33][4];
				int var8 = (var1 << 2) + var5;
				int var9 = (var2 << 2) + var6;

				for(int var10 = 0; var10 < var7.length; ++var10) {
					var7[var10][0] = this.initializeNoiseField((double)var8, (double)var10, (double)var9);
					var7[var10][1] = this.initializeNoiseField((double)var8, (double)var10, (double)(var9 + 1));
					var7[var10][2] = this.initializeNoiseField((double)(var8 + 1), (double)var10, (double)var9);
					var7[var10][3] = this.initializeNoiseField((double)(var8 + 1), (double)var10, (double)(var9 + 1));
				}

				for(var8 = 0; var8 < 32; ++var8) {
					var50 = var7[var8][0];
					double var11 = var7[var8][1];
					double var13 = var7[var8][2];
					double var15 = var7[var8][3];
					double var17 = var7[var8 + 1][0];
					double var19 = var7[var8 + 1][1];
					double var21 = var7[var8 + 1][2];
					double var23 = var7[var8 + 1][3];

					for(int var25 = 0; var25 < 4; ++var25) {
						double var26 = (double)var25 / 4.0D;
						double var28 = var50 + (var17 - var50) * var26;
						double var30 = var11 + (var19 - var11) * var26;
						double var32 = var13 + (var21 - var13) * var26;
						double var34 = var15 + (var23 - var15) * var26;

						for(int var55 = 0; var55 < 4; ++var55) {
							double var37 = (double)var55 / 4.0D;
							double var39 = var28 + (var32 - var28) * var37;
							double var41 = var30 + (var34 - var30) * var37;
							int var27 = var55 + (var5 << 2) << 11 | 0 + (var6 << 2) << 7 | (var8 << 2) + var25;

							for(int var36 = 0; var36 < 4; ++var36) {
								double var45 = (double)var36 / 4.0D;
								double var47 = var39 + (var41 - var39) * var45;
								int var56 = 0;
								if((var8 << 2) + var25 < 64) {
									var56 = Block.waterStill.blockID;
								}

								if(var47 > 0.0D) {
									var56 = Block.stone.blockID;
								}

								var3[var27] = (byte)var56;
								var27 += 128;
							}
						}
					}
				}
			}
		}

		for(var5 = 0; var5 < 16; ++var5) {
			for(var6 = 0; var6 < 16; ++var6) {
				double var49 = (double)((var1 << 4) + var5);
				var50 = (double)((var2 << 4) + var6);
				boolean var51 = this.noiseGen4.generateNoiseOctaves(var49 * (1.0D / 32.0D), var50 * (1.0D / 32.0D), 0.0D) + this.rand.nextDouble() * 0.2D > 0.0D;
				boolean var14 = this.noiseGen4.generateNoiseOctaves(var50 * (1.0D / 32.0D), 109.0134D, var49 * (1.0D / 32.0D)) + this.rand.nextDouble() * 0.2D > 3.0D;
				int var52 = (int)(this.noiseGen5.noiseGenerator(var49 * (1.0D / 32.0D) * 2.0D, var50 * (1.0D / 32.0D) * 2.0D) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var16 = var5 << 11 | var6 << 7 | 127;
				int var53 = -1;
				int var18 = Block.grass.blockID;
				int var54 = Block.dirt.blockID;

				for(int var20 = 127; var20 >= 0; --var20) {
					if(var3[var16] == 0) {
						var53 = -1;
					} else if(var3[var16] == Block.stone.blockID) {
						if(var53 == -1) {
							if(var52 <= 0) {
								var18 = 0;
								var54 = (byte)Block.stone.blockID;
							} else if(var20 >= 60 && var20 <= 65) {
								var18 = Block.grass.blockID;
								var54 = Block.dirt.blockID;
								if(var14) {
									var18 = 0;
								}

								if(var14) {
									var54 = Block.gravel.blockID;
								}

								if(var51) {
									var18 = Block.sand.blockID;
								}

								if(var51) {
									var54 = Block.sand.blockID;
								}
							}

							if(var20 < 64 && var18 == 0) {
								var18 = Block.waterStill.blockID;
							}

							var53 = var52;
							if(var20 >= 63) {
								var3[var16] = (byte)var18;
							} else {
								var3[var16] = (byte)var54;
							}
						} else if(var53 > 0) {
							--var53;
							var3[var16] = (byte)var54;
						}
					}

					--var16;
				}
			}
		}

		var4.generateHeightMap();
		return var4;
	}
	
	/*
	-----------------------------------------------------------------------------------------------------------------------------------------------
	
	I don't think Infdev 20100415's terrain generator covers cave generation as I can't find any semblance of such a thing
	in its source code. Besides, the Minecraft Wiki doesn't document anything about Infdev versions 20100327 to 20100415
	having cave generation. Feel free to take a look for yourself if you're interested:
	
	https://minecraft.wiki/w/Java_Edition_Infdev_20100327
	https://minecraft.wiki/w/Java_Edition_Infdev_20100330-1203
	https://minecraft.wiki/w/Java_Edition_Infdev_20100330-1511
	https://minecraft.wiki/w/Java_Edition_Infdev_20100413-1949
	https://minecraft.wiki/w/Java_Edition_Infdev_20100413-1953
	https://minecraft.wiki/w/Java_Edition_Infdev_20100414
	https://minecraft.wiki/w/Java_Edition_Infdev_20100415
	
	Because of this, I've extended Infdev 20100415's terrain generator to account for cave generation by restoring the
	system Alpha v1.0.0 already had prior to my modifications. It has been placed between the "provideChunk" and
	"initializeNoiseField" classes as this order aligns with where "generateLargeCaveNode", "generateCaveNode", and
	"generateCaves" are located within Alpha v1.0.0's terrain generator.
	
	As a matter of fact, this thing didn't crash the game the first time I tested it, and it was reimplemented as-is!
	
	-----------------------------------------------------------------------------------------------------------------------------------------------
	*/
	
	protected void generateLargeCaveNode(int var1, int var2, byte[] var3, double var4, double var6, double var8) {
		this.generateCaveNode(var1, var2, var3, var4, var6, var8, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
	}
	
	protected void generateCaveNode(int var1, int var2, byte[] var3, double var4, double var6, double var8, float var10, float var11, float var12, int var13, int var14, double var15) {
		double var17 = (double)(var1 * 16 + 8);
		double var19 = (double)(var2 * 16 + 8);
		float var21 = 0.0F;
		float var22 = 0.0F;
		Random var23 = new Random(this.rand.nextLong());
		if(var14 <= 0) {
			byte var24 = 112;
			var14 = var24 - var23.nextInt(var24 / 4);
		}

		boolean var52 = false;
		if(var13 == -1) {
			var13 = var14 / 2;
			var52 = true;
		}

		int var25 = var23.nextInt(var14 / 2) + var14 / 4;

		for(boolean var26 = var23.nextInt(6) == 0; var13 < var14; ++var13) {
			double var27 = 1.5D + (double)(MathHelper.sin((float)var13 * (float)Math.PI / (float)var14) * var10 * 1.0F);
			double var29 = var27 * var15;
			float var31 = MathHelper.cos(var12);
			float var32 = MathHelper.sin(var12);
			var4 += (double)(MathHelper.cos(var11) * var31);
			var6 += (double)var32;
			var8 += (double)(MathHelper.sin(var11) * var31);
			if(var26) {
				var12 *= 0.92F;
			} else {
				var12 *= 0.7F;
			}

			var12 += var22 * 0.1F;
			var11 += var21 * 0.1F;
			var22 *= 0.9F;
			var21 *= 12.0F / 16.0F;
			var22 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 2.0F;
			var21 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 4.0F;
			if(!var52 && var13 == var25 && var10 > 1.0F) {
				this.generateCaveNode(var1, var2, var3, var4, var6, var8, var23.nextFloat() * 0.5F + 0.5F, var11 - (float)Math.PI * 0.5F, var12 / 3.0F, var13, var14, 1.0D);
				this.generateCaveNode(var1, var2, var3, var4, var6, var8, var23.nextFloat() * 0.5F + 0.5F, var11 + (float)Math.PI * 0.5F, var12 / 3.0F, var13, var14, 1.0D);
				return;
			}

			if(var52 || var23.nextInt(4) != 0) {
				double var33 = var4 - var17;
				double var35 = var8 - var19;
				double var37 = (double)(var14 - var13);
				double var39 = (double)(var10 + 2.0F + 16.0F);
				if(var33 * var33 + var35 * var35 - var37 * var37 > var39 * var39) {
					return;
				}

				if(var4 >= var17 - 16.0D - var27 * 2.0D && var8 >= var19 - 16.0D - var27 * 2.0D && var4 <= var17 + 16.0D + var27 * 2.0D && var8 <= var19 + 16.0D + var27 * 2.0D) {
					int var53 = MathHelper.floor_double(var4 - var27) - var1 * 16 - 1;
					int var34 = MathHelper.floor_double(var4 + var27) - var1 * 16 + 1;
					int var54 = MathHelper.floor_double(var6 - var29) - 1;
					int var36 = MathHelper.floor_double(var6 + var29) + 1;
					int var55 = MathHelper.floor_double(var8 - var27) - var2 * 16 - 1;
					int var38 = MathHelper.floor_double(var8 + var27) - var2 * 16 + 1;
					if(var53 < 0) {
						var53 = 0;
					}

					if(var34 > 16) {
						var34 = 16;
					}

					if(var54 < 1) {
						var54 = 1;
					}

					if(var36 > 120) {
						var36 = 120;
					}

					if(var55 < 0) {
						var55 = 0;
					}

					if(var38 > 16) {
						var38 = 16;
					}

					boolean var56 = false;

					int var40;
					int var43;
					for(var40 = var53; !var56 && var40 < var34; ++var40) {
						for(int var41 = var55; !var56 && var41 < var38; ++var41) {
							for(int var42 = var36 + 1; !var56 && var42 >= var54 - 1; --var42) {
								var43 = (var40 * 16 + var41) * 128 + var42;
								if(var42 >= 0 && var42 < 128) {
									if(var3[var43] == Block.waterMoving.blockID || var3[var43] == Block.waterStill.blockID) {
										var56 = true;
									}

									if(var42 != var54 - 1 && var40 != var53 && var40 != var34 - 1 && var41 != var55 && var41 != var38 - 1) {
										var42 = var54;
									}
								}
							}
						}
					}

					if(!var56) {
						for(var40 = var53; var40 < var34; ++var40) {
							double var57 = ((double)(var40 + var1 * 16) + 0.5D - var4) / var27;

							for(var43 = var55; var43 < var38; ++var43) {
								double var44 = ((double)(var43 + var2 * 16) + 0.5D - var8) / var27;
								int var46 = (var40 * 16 + var43) * 128 + var36;
								boolean var47 = false;

								for(int var48 = var36 - 1; var48 >= var54; --var48) {
									double var49 = ((double)var48 + 0.5D - var6) / var29;
									if(var49 > -0.7D && var57 * var57 + var49 * var49 + var44 * var44 < 1.0D) {
										byte var51 = var3[var46];
										if(var51 == Block.grass.blockID) {
											var47 = true;
										}

										if(var51 == Block.stone.blockID || var51 == Block.dirt.blockID || var51 == Block.grass.blockID) {
											if(var48 < 10) {
												var3[var46] = (byte)Block.lavaMoving.blockID;
											} else {
												var3[var46] = 0;
												if(var47 && var3[var46 - 1] == Block.dirt.blockID) {
													var3[var46 - 1] = (byte)Block.grass.blockID;
												}
											}
										}
									}

									--var46;
								}
							}
						}

						if(var52) {
							break;
						}
					}
				}
			}
		}

	}

	private void generateCaves(int var1, int var2, byte[] var3) {
		byte var4 = 8;
		this.rand.setSeed(this.worldObj.randomSeed);
		long var5 = this.rand.nextLong() / 2L * 2L + 1L;
		long var7 = this.rand.nextLong() / 2L * 2L + 1L;

		for(int var9 = var1 - var4; var9 <= var1 + var4; ++var9) {
			for(int var10 = var2 - var4; var10 <= var2 + var4; ++var10) {
				this.rand.setSeed((long)var9 * var5 + (long)var10 * var7 ^ this.worldObj.randomSeed);
				int var11 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);
				if(this.rand.nextInt(15) != 0) {
					var11 = 0;
				}

				for(int var12 = 0; var12 < var11; ++var12) {
					double var13 = (double)(var9 * 16 + this.rand.nextInt(16));
					double var15 = (double)this.rand.nextInt(this.rand.nextInt(120) + 8);
					double var17 = (double)(var10 * 16 + this.rand.nextInt(16));
					int var19 = 1;
					if(this.rand.nextInt(4) == 0) {
						this.generateLargeCaveNode(var1, var2, var3, var13, var15, var17);
						var19 += this.rand.nextInt(4);
					}

					for(int var20 = 0; var20 < var19; ++var20) {
						float var21 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
						float var22 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
						float var23 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
						this.generateCaveNode(var1, var2, var3, var13, var15, var17, var23, var21, var22, 0, 0, 1.0D);
					}
				}
			}
		}

	}
	// End of Alpha v1.0.0 cave generation

	private double initializeNoiseField(double var1, double var3, double var5) {
		double var7 = var3 * 4.0D - 64.0D;
		if(var7 < 0.0D) {
			var7 *= 3.0D;
		}

		double var9 = this.noiseGen3.generateNoiseOctaves(var1 * 684.412D / 80.0D, var3 * 684.412D / 400.0D, var5 * 684.412D / 80.0D) / 2.0D;
		double var11;
		double var13;
		if(var9 < -1.0D) {
			var11 = this.noiseGen1.generateNoiseOctaves(var1 * 684.412D, var3 * 984.412D, var5 * 684.412D) / 512.0D;
			var13 = var11 - var7;
			if(var13 < -10.0D) {
				var13 = -10.0D;
			}

			if(var13 > 10.0D) {
				var13 = 10.0D;
			}
		} else if(var9 > 1.0D) {
			var11 = this.noiseGen2.generateNoiseOctaves(var1 * 684.412D, var3 * 984.412D, var5 * 684.412D) / 512.0D;
			var13 = var11 - var7;
			if(var13 < -10.0D) {
				var13 = -10.0D;
			}

			if(var13 > 10.0D) {
				var13 = 10.0D;
			}
		} else {
			double var15 = this.noiseGen1.generateNoiseOctaves(var1 * 684.412D, var3 * 984.412D, var5 * 684.412D) / 512.0D - var7;
			double var17 = this.noiseGen2.generateNoiseOctaves(var1 * 684.412D, var3 * 984.412D, var5 * 684.412D) / 512.0D - var7;
			if(var15 < -10.0D) {
				var15 = -10.0D;
			}

			if(var15 > 10.0D) {
				var15 = 10.0D;
			}

			if(var17 < -10.0D) {
				var17 = -10.0D;
			}

			if(var17 > 10.0D) {
				var17 = 10.0D;
			}

			double var19 = (var9 + 1.0D) / 2.0D;
			var11 = var15 + (var17 - var15) * var19;
			var13 = var11;
		}

		return var13;
	}

	public boolean chunkExists(int var1, int var2) {
		return true;
	}

	public void populate(IChunkProvider var1, int var2, int var3) {
		this.rand.setSeed((long)var2 * 318279123L + (long)var3 * 919871212L);
		int var8 = var2 << 4;
		var2 = var3 << 4;

		int var4;
		int var5;
		int var6;
		
		/*
		-------------------------------------------------------------------------------------------------------------------------------------------
		
		To complement the cave systems, I've restored Alpha v1.0.0's dungeon generation since
		Infdev 20100415's terrain generator doesn't have such a thing.
		
		Please keep in mind that I haven't dug underground to find out how it affects cave generation.
		
		-------------------------------------------------------------------------------------------------------------------------------------------
		*/
		
		for(var3 = 0; var3 < 4; ++var3) {
			var4 = var8 + this.rand.nextInt(16) + 8;
			var5 = this.rand.nextInt(128);
			var6 = var2 + this.rand.nextInt(16) + 8;
			(new WorldGenDungeons()).generate(this.worldObj, this.rand, var4, var5, var6);
		}
		// End of Alpha v1.0.0 dungeon generation
		
		/*
		-------------------------------------------------------------------------------------------------------------------------------------------
		
		Incorporate additional generation of dirt and gravel blocks to complement the voxelated landscapes.
		The following is taken from Alpha v1.0.0.
		
		-------------------------------------------------------------------------------------------------------------------------------------------
		*/
		
		for(var3 = 0; var3 < 20; ++var3) {
			var4 = var8 + this.rand.nextInt(16);
			var5 = this.rand.nextInt(128);
			var6 = var5 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.dirt.blockID)).generate(this.worldObj, this.rand, var4, var5, var6);
		}

		for(var3 = 0; var3 < 10; ++var3) {
			var4 = var8 + this.rand.nextInt(16);
			var5 = this.rand.nextInt(128);
			var6 = var5 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.gravel.blockID)).generate(this.worldObj, this.rand, var4, var5, var6);
		}
		// End of additional generation of dirt and gravel blocks
		
		for(var3 = 0; var3 < 20; ++var3) {
			var4 = var8 + this.rand.nextInt(16);
			var5 = this.rand.nextInt(128);
			var6 = var2 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreCoal.blockID)).generate(this.worldObj, this.rand, var4, var5, var6);
		}

		for(var3 = 0; var3 < 10; ++var3) {
			var4 = var8 + this.rand.nextInt(16);
			var5 = this.rand.nextInt(64);
			var6 = var2 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreIron.blockID)).generate(this.worldObj, this.rand, var4, var5, var6);
		}

		if(this.rand.nextInt(2) == 0) {
			var3 = var8 + this.rand.nextInt(16);
			var4 = this.rand.nextInt(32);
			var5 = var2 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreGold.blockID)).generate(this.worldObj, this.rand, var3, var4, var5);
		}

		if(this.rand.nextInt(8) == 0) {
			var3 = var8 + this.rand.nextInt(16);
			var4 = this.rand.nextInt(16);
			var5 = var2 + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreDiamond.blockID)).generate(this.worldObj, this.rand, var3, var4, var5);
		}

		var3 = (int)this.mobSpawnerNoise.noiseGenerator((double)var8 * 0.25D, (double)var2 * 0.25D) << 3;
		WorldGenBigTree var9 = new WorldGenBigTree();

		for(var5 = 0; var5 < var3; ++var5) {
			var6 = var8 + this.rand.nextInt(16) + 8;
			int var7 = var2 + this.rand.nextInt(16) + 8;
			var9.setScale(1.0D, 1.0D, 1.0D);
			var9.generate(this.worldObj, this.rand, var6, this.worldObj.getHeightValue(var6, var7), var7);
		}
		
		/*
		-------------------------------------------------------------------------------------------------------------------------------------------
		
		Why have only one type of tree when we can have differing sizes? That question has motivated me to restore
		Alpha v1.0.0's tree generation in hopes of having it coexist with Infdev 20100415's big trees.
		
		-------------------------------------------------------------------------------------------------------------------------------------------
		*/
		
		WorldGenTrees var18 = new WorldGenTrees();
		if(this.rand.nextInt(10) == 0) {
			++var3;
		}
		
		int var16;
		for(var5 = 0; var5 < var3; ++var5) {
			var6 = var8 + this.rand.nextInt(16) + 8;
			var16 = var5 + this.rand.nextInt(16) + 8;
			var18.setScale(1.0D, 1.0D, 1.0D);
			var18.generate(this.worldObj, this.rand, var6, this.worldObj.getHeightValue(var6, var16), var16);
		}
		// End of Alpha v1.0.0 tree generation
		
		/*
		-------------------------------------------------------------------------------------------------------------------------------------------
		
		Restore Alpha v1.0.0's generation of flowers and mushrooms to further decorate the landscape!
		
		-------------------------------------------------------------------------------------------------------------------------------------------
		*/
		
		int var17;
		for(var5 = 0; var5 < 2; ++var5) {
			var6 = var8 + this.rand.nextInt(16) + 8;
			var16 = this.rand.nextInt(128);
			var17 = var5 + this.rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.plantYellow.blockID)).generate(this.worldObj, this.rand, var6, var16, var17);
		}

		if(this.rand.nextInt(2) == 0) {
			var5 = var8 + this.rand.nextInt(16) + 8;
			var6 = this.rand.nextInt(128);
			var16 = var5 + this.rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.plantRed.blockID)).generate(this.worldObj, this.rand, var5, var6, var16);
		}

		if(this.rand.nextInt(4) == 0) {
			var5 = var8 + this.rand.nextInt(16) + 8;
			var6 = this.rand.nextInt(128);
			var16 = var5 + this.rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(this.worldObj, this.rand, var5, var6, var16);
		}

		if(this.rand.nextInt(8) == 0) {
			var5 = var8 + this.rand.nextInt(16) + 8;
			var6 = this.rand.nextInt(128);
			var16 = var5 + this.rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(this.worldObj, this.rand, var5, var6, var16);
		}
		// End of Alpha v1.0.0 flower and mushroom generation
		
		/*
		-------------------------------------------------------------------------------------------------------------------------------------------
		
		Restore Alpha v1.0.0 generation of moving liquids. It creates larger bodies of water and introduces waterfalls.
		In regard to lava, there is a chance that lavafalls will generate.
		
		-------------------------------------------------------------------------------------------------------------------------------------------
		*/
		
		for(var5 = 0; var5 < 50; ++var5) {
			var6 = var8 + this.rand.nextInt(16) + 8;
			var16 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			var17 = var5 + this.rand.nextInt(16) + 8;
			(new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.worldObj, this.rand, var6, var16, var17);
		}

		for(var5 = 0; var5 < 20; ++var5) {
			var6 = var8 + this.rand.nextInt(16) + 8;
			var16 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(112) + 8) + 8);
			var17 = var5 + this.rand.nextInt(16) + 8;
			(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.worldObj, this.rand, var6, var16, var17);
		}
		// End of Alpha v1.0.0 moving liquid generation

	}
    
	/*
	-----------------------------------------------------------------------------------------------------------------------------------------------
	
	Due to concerns regarding software stability, I've left the following strings of code the way they are
	in Alpha v1.0.0's terrain generator.
	
	-----------------------------------------------------------------------------------------------------------------------------------------------
	*/
	
	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		return true;
	}

	public boolean unload100OldestChunks() {
		return false;
	}

	public boolean canSave() {
		return true;
	}
}
