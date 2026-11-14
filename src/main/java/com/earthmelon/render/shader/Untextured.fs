#version 450 core

in float same_normal;

out vec4 out_Colour;

uniform vec4 tintColour;

void main(){
	out_Colour = vec4(tintColour.r * same_normal, tintColour.g * same_normal, tintColour.b * same_normal, tintColour.a);
}