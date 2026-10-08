#version 450 core

out vec4 out_Colour;

uniform vec4 tintColour;

void main(){
	out_Colour = tintColour;
}