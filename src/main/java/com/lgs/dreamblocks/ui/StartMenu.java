/*
 _(`-')      (`-')  (`-')  _(`-')  _ <-. (`-')  <-.(`-')                               <-.(`-')  (`-').->
( (OO ).-><-.(OO )  ( OO).-/(OO ).-/    \(OO )_  __( OO)    <-.        .->    _         __( OO)  ( OO)_
 \    .'_ ,------,)(,------./ ,---.  ,--./  ,-.)'-'---.\  ,--. )  (`-')----.  \-,-----.'-'. ,--.(_)--\_)
 '`'-..__)|   /`. ' |  .---'| \ /`.\ |   `.'   || .-. (/  |  (`-')( OO).-.  '  |  .--./|  .'   //    _ /
 |  |  ' ||  |_.' |(|  '--. '-'|_.' ||  |'.'|  || '-' `.) |  |OO )( _) | |  | /_) (`-')|      /)\_..`--.
 |  |  / :|  .   .' |  .--'(|  .-.  ||  |   |  || /`'.  |(|  '__ | \|  |)|  | ||  |OO )|  .   ' .-._)   \
 |  '-'  /|  |\  \  |  `---.|  | |  ||  |   |  || '--'  / |     |'  '  '-'  '(_'  '--'\|  |\   \\       /
 `------' `--' '--' `------'`--' `--'`--'   `--'`------'  `-----'    `-----'    `-----'`--' '--' `-----'
                                     _                    _ ___
                                    |_) _    _ ._  _|  __|_  |o._ _  _
                                    |_)(/_\/(_)| |(_| (_)|   ||| | |(/_
                                          /


is free software: you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by the
Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


package com.lgs.dreamblocks.ui;

import com.lgs.dreamblocks.GraphicsHandler;
import com.lgs.dreamblocks.MainGame;
import com.lgs.dreamblocks.Sprite;
import com.lgs.dreamblocks.SpriteStore;

public class StartMenu {
	
	/* menu sprites */
	private final Sprite menu_bgTile = SpriteStore.get().getSprite("sprites/tiles/cobble.png");
	private final Sprite menu_logo = SpriteStore.get().getSprite("sprites/menus/title.png");
	private final Button menuNew;
	private final Button menuLoad;
	private final Button menuQuit;
	private final Sprite menu_tag = SpriteStore.get().getSprite("sprites/menus/tag.png");

	private MainGame game;
	
	public StartMenu(MainGame mainGame) {
		this.game = mainGame;
		SpriteStore ss = SpriteStore.get();
		menuNew = new Button(150, 160, 64, ss.getSprite("sprites/menus/new_up.png"),  ss.getSprite("sprites/menus/new_down.png"));
		menuLoad = new Button(250, 160, 64, ss.getSprite("sprites/menus/load_up.png"), ss.getSprite("sprites/menus/load_down.png"));
		menuQuit = new Button(350, 160, 64, ss.getSprite("sprites/menus/quit_up.png"), ss.getSprite("sprites/menus/quit_down.png"));
	}
	// menu title + animated logo
	public void draw(GraphicsHandler g) {
		game.drawTileBackground(g, menu_bgTile, 32);
		game.drawCenteredX(g, menu_logo, 70, 397, 50);
		float tagScale = ((float) Math.abs((game.ticksRunning % 100) - 50)) / 50 + 1;
		menu_tag.draw(g, 610, 60, (int) (60 * tagScale), (int) (37 * tagScale));

		int mouseY = game.screenMousePos.y;
		int mouseX = game.screenMousePos.x;
		if (menuNew.isInside(mouseX, mouseY)){
			menuNew.drawHover(g);
		} else {
			menuNew.drawUp(g);
		}

		if (menuLoad.isInside(mouseX, mouseY)){
			menuLoad.drawHover(g);
		} else {
			menuLoad.drawUp(g);
		}

		if (menuQuit.isInside(mouseX, mouseY)){
			menuQuit.drawHover(g);
		} else {
			menuQuit.drawUp(g);
		}
	}

	public START_MENU_DIALOG_RESULT handleClick(int x, int y){
	    if (menuNew.isInside(x, y)){
	        return START_MENU_DIALOG_RESULT.NEW_GAME;
        } else if (menuLoad.isInside(x, y)){
            return START_MENU_DIALOG_RESULT.LOAD_GAME;
        } else {
            return START_MENU_DIALOG_RESULT.QUIT_GAME;
        }
    }
}
