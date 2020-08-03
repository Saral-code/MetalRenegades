// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.metalrenegades.world.dynamic.discoverables;

import org.terasology.math.Region3i;
import org.terasology.math.TeraMath;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.WhiteNoise;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

/**
 * Places chests into {@link DiscoverablesFacet} across the surface of the game world
 */
@Produces(DiscoverablesFacet.class)
@Requires(@Facet(value = SurfaceHeightFacet.class))
public class DiscoverablesProvider implements FacetProvider {

    /**
     * The probability out of one that a chest will be placed on any particular surface block.
     */
    public final static float CHEST_PROBABILITY = 0.00005f;

    /**
     * A noise provider that determines the positions of discoverables.
     */
    private Noise noise;

    @Override
    public void setSeed(long seed) {
        noise = new WhiteNoise(seed);
    }

    @Override
    public void process(GeneratingRegion region) {
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);
        DiscoverablesFacet facet = new DiscoverablesFacet(region.getRegion(), region.getBorderForFacet(DiscoverablesFacet.class));

        Region3i worldRegion = facet.getWorldRegion();

        for (int wz = worldRegion.minZ(); wz <= worldRegion.maxZ(); wz++) {
            for (int wx = worldRegion.minX(); wx <= worldRegion.maxX(); wx++) {
                int surfaceHeight = TeraMath.floorToInt(surfaceHeightFacet.getWorld(wx, wz));

                if (surfaceHeight > facet.getWorldRegion().minY() &&
                    surfaceHeight < facet.getWorldRegion().maxY()) {
                    if (noise.noise(wx, wz) < (CHEST_PROBABILITY * 2) - 1) {
                        facet.setWorld(wx, surfaceHeight + 1, wz, new DiscoverablesChest());
                    }
                }
            }
        }

        region.setRegionFacet(DiscoverablesFacet.class, facet);
    }
}
