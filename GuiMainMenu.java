package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class GuiMainMenu extends GuiScreen {
	private float updateCounter = 0.0F;
	private String[] splashes = new String[]{"Pre-beta!", "As seen on TV!", "Awesome!", "100% pure!", "May contain nuts!", "Better than Prey!", "More polygons!", "Sexy!", "Limited edition!", "Flashing letters!", "Made by Notch!", "Coming soon!", "Best in class!", "When it\'s finished!", "Absolutely dragon free!", "Excitement!", "More than 5000 sold!", "One of a kind!", "700+ hits on YouTube!", "Indev!", "Spiders everywhere!", "Check it out!", "Holy cow, man!", "It\'s a game!", "Made in Sweden!", "Uses LWJGL!", "Reticulating splines!", "Minecraft!", "Yaaay!", "Alpha version!", "Singleplayer!", "Keyboard compatible!", "Undocumented!", "Ingots!", "Exploding creepers!", "That\'s not a moon!", "l33t!", "Create!", "Survive!", "Dungeon!", "Exclusive!", "The bee\'s knees!", "Down with O.P.P.!", "Closed source!", "Classy!", "Wow!", "Not on steam!", "9.95 euro!", "Half price!", "Oh man!", "Check it out!", "Awesome community!", "Pixels!", "Teetsuuuuoooo!", "Kaaneeeedaaaa!", "Now with difficulty!", "Enhanced!", "90% bug free!", "Pretty!", "12 herbs and spices!", "Fat free!", "Absolutely no memes!", "Free dental!", "Ask your doctor!", "Minors welcome!", "Cloud computing!", "Legal in Finland!", "Hard to label!", "Technically good!", "Bringing home the bacon!", "Indie!", "GOTY!", "Ceci n\'est pas une title screen!", "Euclidian!", "Now in 3D!", "Inspirational!", "Herregud!", "Complex cellular automata!", "Yes, sir!", "Played by cowboys!", "OpenGL 1.1!", "Thousands of colors!", "Try it!", "Age of Wonders is better!", "Try the mushroom stew!", "Sensational!", "Hot tamale, hot hot tamale!", "Play him off, keyboard cat!", "Guaranteed!", "Macroscopic!", "Bring it on!", "Random splash!", "Call your mother!", "Monster infighting!", "Loved by millions!", "Ultimate edition!", "Freaky!", "You\'ve got a brand new key!", "Water proof!", "Uninflammable!", "Whoa, dude!", "All inclusive!", "Tell your friends!", "NP is not in P!", "Notch <3 Ez!", "Music by C418!"};
	private String splashString = this.splashes[(int)(Math.random() * (double)this.splashes.length)];

	public void updateScreen() {
		this.updateCounter += 0.01F;
	}

	protected void keyTyped(char var1, int var2) {
	}

	public void initGui() {
		this.controlList.clear();
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48, "Single player"));
		this.controlList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 72, "Multi player"));
		this.controlList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 96, "Play tutorial level"));
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Options..."));
		((GuiButton)this.controlList.get(1)).enabled = false;
		((GuiButton)this.controlList.get(2)).enabled = false;
		if(this.mc.session == null) {
			((GuiButton)this.controlList.get(1)).enabled = false;
		}

	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.id == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		}

		if(var1.id == 1) {
			this.mc.displayGuiScreen(new GuiCreateWorld(this));
		}

	}

	public void drawScreen(int var1, int var2, float var3) {
		
		/*
		-------------------------------------------------------------------------------------------------------------------------------------------
		
		Back when Minecraft: Java Edition received a title screen, the dirt background scrolled vertically, however, this was removed
		when Indev 20100206-2034 released, at least according to the Minecraft Wiki: https://minecraft.wiki/w/Title_Screen
		
		The title screen was added in Indev 20100131-2156: https://minecraft.wiki/w/Java_Edition_Indev_0.31_20100131-2156
		
		Why do I have this constant need to have "proof" of what I'm talking about? All just to satisfy a few so skeptical
		individuals who likely aren't going to be able to provide good emotional support anyway? Why fucking bother with
		this shit when I can simply focus on the people who care about me, aren't going to question the legitimacy of my
		words, and not only are forgiving enough and willing to provide emotional support during my times of need, but are
		also good at providing emotional support? Satisfying the demands and expectations of skeptical people simply isn't
		worth my time and energy in my eyes.
		
		Anyway...
		
		Because I like the vertical scrolling Minecraft Indev had a certain point, I've decided to restore it by decompiling versions
		Indev 20100202-2330 and Indev 20100202-2330, compare their title screen code in order to identify the bits responsible for
		performing the vertical scroll, take the chunk of code I've successfully identified, and place it where it would've been
		in Indev 20100202-2330 (while also replacing the Tesselator's "var3" variable with "var4" and its seven proceeding mentions
		before getting to GL11.glBindTexture so it can compile successfully).
		
		-------------------------------------------------------------------------------------------------------------------------------------------
		*/
		
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		Tessellator var4 = Tessellator.instance;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/dirt.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		var4.startDrawingQuads();
		var4.setColorOpaque_I(4210752);
		var4.addVertexWithUV(0.0F, (float)this.height, 0.0F, 0.0F, (float)this.height / 32.0F + this.updateCounter);
		var4.addVertexWithUV((float)this.width, (float)this.height, 0.0F, (float)this.width / 32.0F, (float)this.height / 32.0F + this.updateCounter);
		var4.addVertexWithUV((float)this.width, 0.0F, 0.0F, (float)this.width / 32.0F, 0.0F + this.updateCounter);
		var4.addVertexWithUV(0.0F, 0.0F, 0.0F, 0.0F, 0.0F + this.updateCounter);
		var4.draw();
		// End of Indev 20100202-2330 vertal scroll
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/logo.png"));
		short var5 = 256;
		byte var6 = 49;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		var4.setColorOpaque_I(16777215);
		this.drawTexturedModalRect((this.width - var5) / 2, 30, 0, 0, var5, var6);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		float var7 = 1.8F - MathHelper.abs(MathHelper.sin((float)(System.currentTimeMillis() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
		var7 = var7 * 100.0F / (float)(this.fontRenderer.getStringWidth(this.splashString) + 32);
		GL11.glScalef(var7, var7, var7);
		this.drawCenteredString(this.fontRenderer, this.splashString, 0, -8, 16776960);
		GL11.glPopMatrix();
		String var8 = "Copyright Mojang Specifications. Do not distribute.";
		this.drawString(this.fontRenderer, var8, this.width - this.fontRenderer.getStringWidth(var8) - 2, this.height - 10, 16777215);
		long var9 = Runtime.getRuntime().maxMemory();
		long var11 = Runtime.getRuntime().totalMemory();
		long var13 = Runtime.getRuntime().freeMemory();
		long var15 = var9 - var13;
		var8 = "Free memory: " + var15 * 100L / var9 + "% of " + var9 / 1024L / 1024L + "MB";
		this.drawString(this.fontRenderer, var8, this.width - this.fontRenderer.getStringWidth(var8) - 2, 2, 8421504);
		var8 = "Allocated memory: " + var11 * 100L / var9 + "% (" + var11 / 1024L / 1024L + "MB)";
		this.drawString(this.fontRenderer, var8, this.width - this.fontRenderer.getStringWidth(var8) - 2, 12, 8421504);
		super.drawScreen(var1, var2, var3);
	}
}
