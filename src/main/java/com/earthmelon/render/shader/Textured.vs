#version 450 core

in vec3 position;
in vec2 texCoords;

out vec2 pass_texCoords;

uniform mat4 ppmatrix;

void main(void){
	gl_Position = vec4(position, 1.0) * ppmatrix;
	pass_texCoords = texCoords;
}