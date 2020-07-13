/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.metalrenegades.ai.component;

import org.terasology.entitySystem.Component;
import org.terasology.metalrenegades.ai.CitizenNeed;
import org.terasology.network.Replicate;

import java.util.EnumMap;
import java.util.Map;

/**
 * Component which keeps track of a citizens current need status.
 */
public class NeedsComponent implements Component {

    @Replicate
    public CitizenNeed hungerNeed;

    @Replicate
    public CitizenNeed thirstNeed;

    @Replicate
    public CitizenNeed socialNeed;

    @Replicate
    public CitizenNeed restNeed;

    public NeedsComponent(CitizenNeed hunger, CitizenNeed thirst, CitizenNeed social, CitizenNeed rest) {
        this.hungerNeed = hunger;
        this.thirstNeed = thirst;
        this.socialNeed = social;
        this.restNeed = rest;
    }

    public NeedsComponent() {

    }

}
