#version 450 core

in vec2 pass_texCoords;

out vec4 out_Colour;

uniform sampler2D textureSampler;
uniform vec4 tintColour;

void main(){
	out_Colour = texture(textureSampler,pass_texCoords) * tintColour;
}