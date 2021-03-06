/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package us.eunoians.mcrpg.util.blockmeta.chunkmeta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This code is not mine. It is copyright from the original mcMMO allowed for use by their license. Modified 12/7/18
 * It was released under the GPLv3 license
 */

public class McMMOSimpleChunkBuffer extends ByteArrayOutputStream {
  final McMMOSimpleRegionFile rf;
  final int index;

  McMMOSimpleChunkBuffer(McMMOSimpleRegionFile rf, int index){
	super(1024);
	this.rf = rf;
	this.index = index;
  }

  @Override
  public void close() throws IOException{
	rf.write(index, buf, count);
  }
}
