#version 450 core

in vec2 position;
in vec2 texCoords;

out vec2 pass_texCoords;

void main(void){
	gl_Position = vec4(position.x, position.y, -1, 1.0);
	pass_texCoords = texCoords;
}